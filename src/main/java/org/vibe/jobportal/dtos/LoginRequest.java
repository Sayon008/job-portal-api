package org.vibe.jobportal.dtos;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
