package com.dassault_systemes.diy.service;

import com.dassault_systemes.diy.domain.User;
import com.dassault_systemes.diy.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getByPersonalNumber(String personalNumber);

    List<User> getAll();

    void delete(String personalNumber);

    User create(UserDTO userDTO);

    void update(String id, UserDTO user);
}
