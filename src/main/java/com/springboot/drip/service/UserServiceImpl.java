package com.springboot.drip.service;


import com.springboot.drip.model.User;
import com.springboot.drip.model.UserDto;
import com.springboot.drip.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Override
    public User addUser(User user) {
        String password = user.getPassword();
        String encryptedPassword = passwordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }
    @Override
    public User getUserById(Long userId){
        Optional<User> user = userRepository.findById(userId);
        return user.get();
    }
    @Override
    public UserDto getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.map(user ->
                new UserDto(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getRole()
                )
        ).orElse(null);
    }
    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @Override
    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }
    @Override
    public User updateUser(User user){
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return updatedUser;
    }
}
