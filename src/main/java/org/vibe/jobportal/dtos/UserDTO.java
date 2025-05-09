package org.vibe.jobportal.dtos;

import lombok.Data;
import org.vibe.jobportal.model.Role;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private List<Role> roles;
}
