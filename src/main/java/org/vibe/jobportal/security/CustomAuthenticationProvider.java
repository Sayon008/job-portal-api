//package org.vibe.jobportal.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.vibe.jobportal.model.User;
//import org.vibe.jobportal.repository.UserRepository;
//
//import java.util.Collections;
//import java.util.Optional;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        // Step 1: Get username & raw password from login request
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        // Step 2: Fetch user from DB using UserRepository
//        Optional<User> userOptional = userRepository.findByEmail(email);
//        if (userOptional.isEmpty()) {
//            throw new BadCredentialsException("User Not Found");
//        }
//
////        Else get the user from the db
//        User user = userOptional.get();
//
//        // Step 3: Validate password using BCryptPasswordEncoder
//        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
//            throw new BadCredentialsException("Wrong Password");
//        }
//
//        // Step 4: Return authenticated token with roles (for now just empty list)
//        return new UsernamePasswordAuthenticationToken(
//                username,
//                password,
//                Collections.emptyList()
//        );
//
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
