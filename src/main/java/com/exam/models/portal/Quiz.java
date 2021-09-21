package com.exam.models.portal;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor@NoArgsConstructor@Getter@Setter
@Entity
@Table(name="quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String title;
    @Column(length = 512)
    private String description;
    private Integer maxMarks;
    private  Integer noOfQuestions;
    private  boolean active = false;

    @ManyToOne
    @JoinColumn(name = "subject_code")
    private Subject subject;


    @OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Question> questions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<StudentScore>results=new HashSet<>();

}
