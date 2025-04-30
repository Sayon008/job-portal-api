package org.vibe.jobportal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")  // Table name
@Data                   // Lombok - creates getters/setters
@AllArgsConstructor     // Lombok - creates all args constructor
@NoArgsConstructor      // Lombok - creates no args constructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String role;
}
