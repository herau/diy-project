package com.dassault_systemes.diy.web.endpoints;

import com.dassault_systemes.diy.domain.User;
import com.dassault_systemes.diy.service.UserService;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RepositoryRestController
@RequestMapping(value = "/users")
public class UserController extends AbstractController {

    private final UserService service;

    @Inject
    UserController(UserService userService) {
        this.service = userService;
    }

//    @RequestMapping(method = POST)
//    @ResponseStatus(CREATED)
//    void create(@RequestBody UserDTO user, HttpServletResponse response) {
//        String id = user.getPersonalNumber();
//
//        if (service.getByPersonalNumber(id).isPresent()) {
//            throw new EntityAlreadyExistException(id);
//        }
//
//        User result = service.create(user);
//
//        response.setHeader(HttpHeaders.LOCATION, getLocationHeader(result.getPersonalNumber()));
//    }

//    @PreAuthorize("hasAuthority('ADMIN')")
//    @RequestMapping(method = GET)
//    List<User> getAll() {
//        return service.getAll();
//    }

//    @PreAuthorize("@currentUserService.canAccessUser(principal, #id)")
//    @RequestMapping(value = "{id}", method = GET)
//    User get(@PathVariable String id) {
//        return service.getByPersonalNumber(id).orElseThrow(() -> new EntityNotFoundException(id));
//    }
//
//    @PreAuthorize("@currentUserService.canAccessUser(principal, #id)")
//    @RequestMapping(value = "{id}", method = PATCH)
//    void update(@PathVariable String id, @Valid @RequestBody UserDTO userDTO) {
//        service.update(id, userDTO);
//    }
//
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @RequestMapping(value = "{id}", method = DELETE)
//    @ResponseStatus(NO_CONTENT)
//    void delete(@PathVariable String id) {
//        service.delete(id);
//    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/search", method = GET)
    @ResponseBody
    List<User> search(@RequestParam("query") String searchQuery) {
        //TODO make HAL compliant
        if (searchQuery == null || searchQuery.length() < 2) {
            throw new IllegalArgumentException("search query must be contains at least two characters.");
        }
        return service.search(searchQuery);
    }

}
