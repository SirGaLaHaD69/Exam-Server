package com.exam.models.portal;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "questions")
@NoArgsConstructor@AllArgsConstructor@Getter@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 1024)
    private String content;

    private String image;

    @Column(length = 512)
    private String option1;

    @Column(length = 512)
    private String option2;

    @Column(length = 512)
    private String option3;

    @Column(length = 512)
    private String option4;

    @Column(length = 512)
    private String ans;


    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private  Quiz quiz;

}
