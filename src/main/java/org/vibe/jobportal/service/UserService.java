//package org.vibe.jobportal.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.vibe.jobportal.dtos.UserDTO;
//import org.vibe.jobportal.exceptions.UserAlreadyExistException;
//import org.vibe.jobportal.model.Role;
//import org.vibe.jobportal.model.State;
//import org.vibe.jobportal.model.User;
//import org.vibe.jobportal.repository.RoleRepository;
//import org.vibe.jobportal.repository.UserRepository;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserService {
//
//    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    private final RoleRepository roleRepository;
//
//    @Autowired
//    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
//        this.userRepository = userRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//        this.roleRepository = roleRepository;
//    }
//
//    // Method to register a new user.
//    @Transactional  // Ensures all DB actions in this method are atomic
//    public User registerUser(String email, String password, String requestedRole) throws UserAlreadyExistException {
//        // Check if user with the same username already exists
//        Optional<User> userOptional = userRepository.findByEmail(email);
//
//        if(userOptional.isPresent()) {
//            throw new UserAlreadyExistException("User is already present in the System. Please Try Logging.... !!!");
//        }
//
//        // Encrypt the password before saving
//        String encryptedPassword = bCryptPasswordEncoder.encode(password);
//
//        // Create a new User and set the encrypted password
//        User user = new User();
//        user.setEmail(email);
//        user.setPassword(encryptedPassword);
//        user.setCreatedAt(new Date());
//        user.setLastUpdatedAt(new Date());
//        user.setState(State.ACTIVE);
//
//        // 🟡 If requestedRole is null or blank, fallback to "ROLE_USER"
//        String roleToAssign = (requestedRole == null || requestedRole.isEmpty()) ? "ROLE_USER" : requestedRole;
//
//      // ✅ Fetch predefined role from DB
//        Role userRole = roleRepository.findByRoleValue(roleToAssign)
//                        .orElseThrow(() -> new RuntimeException("Default role not found in DB !!" + roleToAssign));
//
//        user.setRoles(List.of(userRole));
//
//        userRepository.save(user);
//
////        Return the newly created user
//        return user;
//    }
//}
