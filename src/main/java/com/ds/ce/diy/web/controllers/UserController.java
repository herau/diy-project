package com.ds.ce.diy.web.controllers;

import com.ds.ce.diy.domain.Company;
import com.ds.ce.diy.domain.State;
import com.ds.ce.diy.domain.User;
import com.ds.ce.diy.domain.VerificationToken;
import com.ds.ce.diy.dto.UserDTO;
import com.ds.ce.diy.dto.UserPasswordDTO;
import com.ds.ce.diy.repositories.UserRepository;
import com.ds.ce.diy.service.TokenService;
import com.ds.ce.diy.service.UserService;
import com.ds.ce.diy.web.exceptions.EntityAlreadyExistException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/api/users", produces = APPLICATION_JSON_VALUE)
public class UserController extends AbstractController {

    private final UserService service;

    private final TokenService tokenService;

    //TODO remove
    @Inject
    UserRepository userRepository;

    @Inject
    UserController(UserService userService, TokenService tokenService) {
        this.service = userService;
        this.tokenService = tokenService;
    }

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    void create(@RequestBody UserDTO user, HttpServletResponse response) {
        String id = user.getPersonalNumber();

        if (service.getByPersonalNumber(id).isPresent()) {
            throw new EntityAlreadyExistException(id);
        }

        User result = service.create(user);

        response.setHeader(HttpHeaders.LOCATION, getLocationHeader(String.valueOf(result.getId())));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = GET)
    List<User> getAll() {
        return service.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN') or principal.id = #id")
    @RequestMapping(value = "{id}", method = GET)
    User get(@PathVariable Integer id) {
        return service.get(id);
    }

    @PreAuthorize("hasAuthority('ADMIN') or principal.id = #id")
    @RequestMapping(value = "{id}", method = PATCH)
    void update(@PathVariable Integer id, @Valid @RequestBody UserDTO userDTO) {
        service.update(id, userDTO);
    }

    @RequestMapping(value = "/token/{token}", method = PATCH)
    void updateWithToken(@PathVariable String token, @Valid @RequestBody UserPasswordDTO userPasswordDTO,
                         HttpServletResponse response) {
        //TODO test
        VerificationToken verifiedToken = tokenService.verifyToken(token);

        if (verifiedToken == null) {
            response.setStatus(NOT_FOUND.value());
            return;
        }

        service.validUser(verifiedToken, userPasswordDTO.getPassword());

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "{id}", method = DELETE)
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(value = "/search", method = GET)
    List<User> search(@RequestParam("query") String searchQuery) {
        if (searchQuery == null || searchQuery.length() < 2) {
            throw new IllegalArgumentException("search query must be contains at least two characters.");
        }
        return service.search(searchQuery);
    }

    //TODO Delete
    @RequestMapping(value = "/test-import", method = POST)
    void test() {
        User user = new User("test", "test", "test", "test", "n27@3ds.com", Company.DS, State.INVALID);
        userRepository.save(user);
        tokenService.sendEmailRegistrationToken(user);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/upload", method = POST)
    void importUsers(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            return;
        }

        //TODO @pfs31110 parse the csv file and use the userService to create or update users
        // cf UserService
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/{id}/token/{type}", method = POST)
        //TODO Test
    void generateNewToken(@RequestParam(value = "type") String type, @RequestParam(value = "id") Integer userId) {
        User user = service.get(userId);

        switch (type) {
            case "registration":
                tokenService.sendEmailRegistrationToken(user);
                break;
            case "password":
                break;
        }
    }
}
