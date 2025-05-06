package org.vibe.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vibe.jobportal.dtos.LoginRequest;
import org.vibe.jobportal.dtos.LoginResponse;
import org.vibe.jobportal.exceptions.PasswordMismatchException;
import org.vibe.jobportal.exceptions.UserAlreadyExistException;
import org.vibe.jobportal.exceptions.UserNotRegisteredException;
import org.vibe.jobportal.model.Role;
import org.vibe.jobportal.model.State;
import org.vibe.jobportal.model.User;
import org.vibe.jobportal.repository.RoleRepository;
import org.vibe.jobportal.repository.UserRepository;
import org.vibe.jobportal.security.JwtUtil;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.vibe.jobportal.utils.UserMapper.toUserDTO;

@Service
public class AuthService {

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtil jwtUtil;


    // Method to register a new user.
    @Transactional  // Ensures all DB actions in this method are atomic
    public User registerUser(String email, String password, String requestedRole) throws UserAlreadyExistException {
        // Check if user with the same username already exists
        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isPresent()) {
            throw new UserAlreadyExistException("User is already present in the System. Please Try Logging.... !!!");
        }

        // Encrypt the password before saving
        String encryptedPassword = bCryptPasswordEncoder.encode(password);

        // Create a new User and set the encrypted password
        User user = new User();
        user.setEmail(email);
        user.setPassword(encryptedPassword);
        user.setCreatedAt(new Date());
        user.setLastUpdatedAt(new Date());
        user.setState(State.ACTIVE);

        // 🟡 If requestedRole is null or blank, fallback to "ROLE_USER"
        String roleToAssign = (requestedRole == null || requestedRole.isEmpty()) ? "ROLE_USER" : requestedRole;

        // ✅ Fetch predefined role from DB
        Role userRole = roleRepository.findByRoleValue(roleToAssign)
                .orElseThrow(() -> new RuntimeException("Default role not found in DB !!" + roleToAssign));

        user.setRoles(List.of(userRole));

        userRepository.save(user);

//        Return the newly created user
        return user;
    }

    public LoginResponse login(LoginRequest loginRequest) throws UserNotRegisteredException, PasswordMismatchException {

        // 1. Check if user exists in DB
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
        if(userOptional.isEmpty()){
            throw new UserNotRegisteredException("Please Register First !!");
        }

        User user = userOptional.get();

        // 2. Validate password
        if(!bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new PasswordMismatchException("Please give correct Password!!!");
        }

        // 3. Generate dummy token (we'll add JWT integration later)
        String token = jwtUtil.generateToken(user.getEmail());

        //Prepare Response
        LoginResponse response = new LoginResponse();
        response.setUser(toUserDTO(user));
        response.setToken(token);

        return response;
    }
}






//We inject JwtUtil into the service.
//
//Upon successful authentication, we generate a JWT token using the user's email.
//
//The token is then included in the LoginResponse.