package com.healthcaresystem.healthcare.controller;

import com.healthcaresystem.healthcare.dto.AuthenticationRequest;
import com.healthcaresystem.healthcare.dto.AuthenticationResponse;
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

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        final UserDetails user = userDetailsService.loadUserByUsername(authRequest.getEmail());
        String token = jwtUtil.generateToken(user.getUsername());

        return new AuthenticationResponse(token);
    }
}
