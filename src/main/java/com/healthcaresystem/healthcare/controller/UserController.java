package com.healthcaresystem.healthcare.controller;

import com.healthcaresystem.healthcare.entity.User;
import com.healthcaresystem.healthcare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/create-with-encryption")
    public User createUserEncrypted(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.createUser(user);
    }


    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/patients")
    @PreAuthorize("hasRole('DOCTOR')")
    public List<User> getAllPatients() {
        return userService.findAllPatients();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PATIENT')")
    @GetMapping("/doctors")
    public List<User> getAllDoctors() {
        return userService.getAllDoctors();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
