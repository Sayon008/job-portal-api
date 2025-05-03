package org.vibe.jobportal.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")  // Table name
@Getter
@Setter
public class User extends BaseModel {

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // A user can have multiple roles (ADMIN, USER, etc.)
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Role> roles;
}
