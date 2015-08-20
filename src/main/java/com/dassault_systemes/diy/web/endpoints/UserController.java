package com.dassault_systemes.diy.web.endpoints;

import com.dassault_systemes.diy.domain.User;
import com.dassault_systemes.diy.dto.UserDTO;
import com.dassault_systemes.diy.service.UserService;
import com.dassault_systemes.diy.web.exceptions.EntityAlreadyExistException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/api/users", produces = APPLICATION_JSON_VALUE)
public class UserController extends AbstractController {

    private final UserService service;

    @Inject
    UserController(UserService userService) {
        this.service = userService;
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

}
