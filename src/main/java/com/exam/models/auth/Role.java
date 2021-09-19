package com.exam.models.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    private  Long roleId;
    private String roleName;
    @JsonIgnore
    @ManyToMany(mappedBy = "roles",cascade = CascadeType.ALL)
    Set<User> users = new HashSet<>();
}
