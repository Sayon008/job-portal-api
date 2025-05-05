package org.vibe.jobportal.dtos;

import lombok.Data;
import org.vibe.jobportal.model.Role;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String role;
}
