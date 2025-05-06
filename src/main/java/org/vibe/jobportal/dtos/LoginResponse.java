package org.vibe.jobportal.dtos;

import lombok.Data;

@Data
public class LoginResponse {
    private UserDTO user;
    private String token;
}
