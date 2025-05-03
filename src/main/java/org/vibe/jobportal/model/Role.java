package org.vibe.jobportal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "roles")
@Entity
public class Role extends BaseModel{
    private String roleValue;
}
