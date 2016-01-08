package com.ds.ce.diy.web.controllers;

import com.ds.ce.diy.domain.User;
import com.ds.ce.diy.domain.security.VerificationToken;
import com.ds.ce.diy.domain.security.VerificationTokenType;
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

import static com.ds.ce.diy.domain.security.VerificationTokenType.EMAIL_REGISTRATION;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    void create(@RequestBody @Valid UserDTO userRequest, HttpServletResponse response) {
        String id = userRequest.getPersonalNumber();

        if (service.getByPersonalNumber(id).isPresent()) {
            throw new EntityAlreadyExistException(id);
        }

        User user = service.create(userRequest);

        VerificationToken userToken = tokenService.createUserToken(user, EMAIL_REGISTRATION);
        tokenService.sendByMail(user, userToken);

        response.setHeader(HttpHeaders.LOCATION, getLocationHeader(String.valueOf(user.getId())));
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

    //    @PreAuthorize("hasAuthority('ADMIN') or principal.id = #id")
    //    @RequestMapping(value = "{id}", method = PATCH)
    //    void update(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
    //        //TODO provide update of only password
    //        service.update(id, userDTO);
    //    }

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

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/upload", method = POST)
    void importUsers(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            return;
        }

        //TODO @pfs31110 parse the csv file and use the userService to create or update users
        // cf UserService
    }

    /**
     * Send a new token to a specific user
     * @param type #com.ds.ce.diy.domain.security.VerificationTokenType
     * @param userId
     */
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/{id}/token/{type}", method = POST)
    public void generateNewToken(@RequestParam(value = "type") VerificationTokenType type, @RequestParam(value = "id") Integer userId) {
        User user = service.get(userId);
        VerificationToken token = tokenService.createUserToken(user, type);
        tokenService.sendByMail(user, token);
    }

    @RequestMapping(value = "/token/{token}", method = PATCH)
    void updateWithToken(@PathVariable(value = "token") String token, @Valid @RequestBody UserPasswordDTO userPasswordDTO) {
        VerificationToken verifiedToken = tokenService.verifyToken(token);
        tokenService.invalidate(verifiedToken);

        User user = verifiedToken.getUser();
        service.changePassword(user, userPasswordDTO.getPassword());
    }

    @RequestMapping(value = "/token/{token}", method = GET)
    public User getUserByToken(@PathVariable(value = "token") String token) {
        //TODO test
        return tokenService.verifyToken(token).getUser();
    }
}
