package com.healthcaresystem.healthcare.service;

import com.healthcaresystem.healthcare.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    Optional<User> findByEmail(String email);

}
