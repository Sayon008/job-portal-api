package org.vibe.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.vibe.jobportal.dtos.LoginDTO;
import org.vibe.jobportal.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(LoginDTO loginDTO) {
        // Authenticate user using AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword())
        );

        if(authentication.isAuthenticated()) {
            return jwtUtil.generateToken(loginDTO.getUsername());
        }
        else{
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
