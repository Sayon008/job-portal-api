package org.vibe.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.vibe.jobportal.dtos.UserDTO;
import org.vibe.jobportal.model.User;
import org.vibe.jobportal.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // Method to register a new user.
    public String registerUser(UserDTO userDTO) {
        // Check if user with the same username already exists
        Optional<User> userOptional = userRepository.findByUsername(userDTO.getUsername());

        if(userOptional.isPresent()) {
            return "User already exists, please try logging....!!!";
        }

        // Encrypt the password before saving
        String encryptedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());

        // Create a new User entity and set the encrypted password
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(encryptedPassword);
        user.setRole(userDTO.getRole());

        userRepository.save(user);

        return "User Registered Successfully!!!";
    }
}
