package org.vibe.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.vibe.jobportal.dtos.UserDTO;
import org.vibe.jobportal.exceptions.UserAlreadyExistException;
import org.vibe.jobportal.model.Role;
import org.vibe.jobportal.model.State;
import org.vibe.jobportal.model.User;
import org.vibe.jobportal.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public User registerUser(UserDTO userDTO) throws UserAlreadyExistException {
        // Check if user with the same username already exists
        Optional<User> userOptional = userRepository.findByEmail(userDTO.getEmail());

        if(userOptional.isPresent()) {
            throw new UserAlreadyExistException("User is already present in the System. Please Try Logging.... !!!");
        }

        // Encrypt the password before saving
        String encryptedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());

        // Create a new User and set the encrypted password
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(encryptedPassword);
        user.setCreatedAt(new Date());
        user.setLastUpdatedAt(new Date());
        user.setState(State.ACTIVE);

//        Create a new default Role for the user
        Role role = new Role();
        role.setRoleValue("ROLE_USER");

        List<Role> roles = new ArrayList<>();
        roles.add(role);

        user.setRoles(roles);

        userRepository.save(user);

//        Return the newly created user
        return user;
    }
}
