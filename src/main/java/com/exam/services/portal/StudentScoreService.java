package com.exam.services.portal;


import com.exam.models.portal.Quiz;
import com.exam.models.portal.Student;
import com.exam.models.portal.StudentScore;
import com.exam.repos.portal.StudentScoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class StudentScoreService {
    @Autowired
    private StudentScoreRepo studentScoreRepo;

//    public Set<StudentScore> getScores(Student student){
//       return (HashSet<StudentScore>) studentScoreRepo.findAllByStudent(student);
//    }
//    public Set<StudentScore> getResults(Quiz quiz){
//        return (HashSet<StudentScore>) studentScoreRepo.findAllByQuiz(quiz);
//    }
    public StudentScore addScore(Student student,Integer marksScored,Integer totalMarks,Quiz quiz){
        StudentScore studentScore = new StudentScore();
        studentScore.setStudent(student);
        studentScore.setQuiz(quiz);
        studentScore.setTotalMarks(totalMarks);
        studentScore.setMarksScored(marksScored);
        return studentScoreRepo.save(studentScore);
    }
}
