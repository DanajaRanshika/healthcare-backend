package com.healthcaresystem.healthcare.controller;

import com.healthcaresystem.healthcare.dto.AuthenticationRequest;
import com.healthcaresystem.healthcare.dto.AuthenticationResponse;
import com.healthcaresystem.healthcare.entity.User;
import com.healthcaresystem.healthcare.repository.UserRepository;
import com.healthcaresystem.healthcare.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        var token = jwtUtil.generateToken(userDetails.getUsername());

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new AuthenticationResponse(token, user.getId(), user.getRole());
    }
}
