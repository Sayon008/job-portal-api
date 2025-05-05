package org.vibe.jobportal.utils;

import org.vibe.jobportal.dtos.UserDTO;
import org.vibe.jobportal.model.User;


public class UserMapper {
    //    Mapper function that will map a User object to UserDTO object
    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        userDTO.setRoles(user.getRoles());

        return userDTO;
    }
}
