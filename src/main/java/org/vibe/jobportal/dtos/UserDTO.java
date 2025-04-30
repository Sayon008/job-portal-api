package org.vibe.jobportal.dtos;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String role;
}
