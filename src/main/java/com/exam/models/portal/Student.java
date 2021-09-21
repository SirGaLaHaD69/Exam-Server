package com.exam.models.portal;

import com.exam.models.auth.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class Student {

    @Id
    private Long userId;

    @JsonIgnore
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    Set<StudentScore> scores= new HashSet<>();

}
