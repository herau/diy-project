package com.ds.ce.diy.service;

import com.ds.ce.diy.domain.User;
import com.ds.ce.diy.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getByPersonalNumber(String personalNumber);

    List<User> getAll();

    void delete(Integer id);

    User get(Integer id);

    User create(UserDTO userDTO);

    void update(Integer id, UserDTO user);

    /**
     * search a User by its firstname, lastname or personal number
     *
     * @param searchQuery full text search query
     *
     * @return users that match the search query
     */
    List<User> search(String searchQuery);

    /**
     * change the password for the user.
     * The state of the user will be set to VALID if it's not the case
     * @param user
     * @param password
     */
    void changePassword(User user, String password);
}
