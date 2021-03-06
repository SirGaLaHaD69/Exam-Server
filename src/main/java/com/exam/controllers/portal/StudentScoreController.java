package com.exam.controllers.portal;


import com.exam.models.auth.JsonMessage;
import com.exam.models.portal.Quiz;
import com.exam.models.portal.Student;
import com.exam.models.portal.StudentScore;
import com.exam.repos.portal.StudentScoreRepo;
import com.exam.services.portal.QuizService;
import com.exam.services.portal.StudentScoreService;
import com.exam.services.portal.StudentService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/score")
@CrossOrigin
public class StudentScoreController {
    @Autowired
    private StudentScoreService studentScoreService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private QuizService quizService;

    @PostMapping("/{userId}/{quizId}")
    public ResponseEntity<?> submitResponse(@PathVariable("userId") Long userId,
                                            @PathVariable("quizId") Long quizId,
                                            @RequestParam("score") Integer marksScored,
                                            @RequestParam("marks") Integer totalMarks){
        Student student = studentService.getStudent(userId);
        if(student==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not Found");
        Quiz quiz =  quizService.getQuiz(quizId);
        if(quiz==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz Not Found");

        return ResponseEntity.ok(studentScoreService.addScore(student,marksScored,totalMarks,quiz));

    }
    @GetMapping("/{userId}/{quizId}")
    public ResponseEntity<?> getResult(@PathVariable("userId") Long userId,
                                       @PathVariable("quizId") Long quizId){
        Student student = studentService.getStudent(userId);
        if(student==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not Found");
        Quiz quiz =  quizService.getQuiz(quizId);
        if(quiz==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz Not Found");
        StudentScore result = studentScoreService.getResult(student, quiz);
        if(result==null){
            JsonMessage jsonMessage = new JsonMessage("NOT_APPEARED_YET");
            return  ResponseEntity.ok(jsonMessage);

        }
        return ResponseEntity.ok(result);

    }
}
