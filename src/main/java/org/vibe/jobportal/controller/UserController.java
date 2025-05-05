//package org.vibe.jobportal.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.vibe.jobportal.dtos.RegisterRequest;
//import org.vibe.jobportal.dtos.UserDTO;
//import org.vibe.jobportal.exceptions.UserAlreadyExistException;
//import org.vibe.jobportal.model.User;
//import org.vibe.jobportal.service.UserService;
//
//import static org.vibe.jobportal.utils.UserMapper.toUserDTO;
//
//@RestController     // Marks this as a REST controller (exposes APIs).
//@RequestMapping("/api/auth")    // Prefix for all API endpoints in this controller.
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/register")       // POST endpoint for user registration.
//    public ResponseEntity<UserDTO> registerUser(@RequestBody RegisterRequest registerRequest) throws UserAlreadyExistException {
//        try{
//            User user = userService.registerUser(registerRequest.getEmail(),registerRequest.getPassword(),registerRequest.getRole());
//            return new ResponseEntity<>(toUserDTO(user), HttpStatus.CREATED);
//        }
//        catch(UserAlreadyExistException exception){
//            throw exception;
//        }
//    }
//}
