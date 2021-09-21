package com.exam.models.portal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
@IdClass(StudentScoreId.class)
public class StudentScore {

    @Id
    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "userId")
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "quiz_id",referencedColumnName = "id")
    private Quiz quiz;

    private Integer marksScored;
    private Integer totalMarks;
}
