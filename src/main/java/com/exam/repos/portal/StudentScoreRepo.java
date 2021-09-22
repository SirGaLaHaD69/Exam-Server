package com.exam.repos.portal;

import com.exam.models.portal.Quiz;
import com.exam.models.portal.Student;
import com.exam.models.portal.StudentScore;
import com.exam.models.portal.StudentScoreId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public  interface StudentScoreRepo extends JpaRepository<StudentScore,StudentScoreId>{
    List<StudentScore> findAllByQuiz(Quiz quiz);
    List<StudentScore> findAllByStudent(Student student);
    StudentScore findByStudentAndQuiz(Student student,Quiz quiz);
}
