package org.vibe.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vibe.jobportal.dtos.UserDTO;
import org.vibe.jobportal.service.UserService;

@RestController     // Marks this as a REST controller (exposes APIs).
@RequestMapping("/api/auth")    // Prefix for all API endpoints in this controller.
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")       // POST endpoint for user registration.
    public String registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }
}
