package com.springboot.drip.service;


import com.springboot.drip.model.User;
import com.springboot.drip.model.UserDto;

import java.util.List;

public interface UserService {
    User addUser(User user);
    UserDto getUserByEmail(String email);
    User getUserById(Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);
    User updateUser(User user);
}
