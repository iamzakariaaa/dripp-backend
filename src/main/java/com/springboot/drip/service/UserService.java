package com.springboot.drip.service;


import com.springboot.drip.model.User;
import com.springboot.drip.dto.UserDTO;

import java.util.List;

public interface UserService {
    User addUser(User user);
    UserDTO getUserByEmail(String email);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    void deleteUser(Long id);
    UserDTO updateUser(UserDTO user);
}
