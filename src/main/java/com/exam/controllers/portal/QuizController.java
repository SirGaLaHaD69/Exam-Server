package com.exam.controllers.portal;


import com.exam.models.portal.Quiz;
import com.exam.services.portal.QuizService;
import com.exam.services.portal.StudentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
@CrossOrigin
public class QuizController {
    @Autowired
    private QuizService quizService;
    @PostMapping("/")
    public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz){
        return ResponseEntity.ok(quizService.addQuiz(quiz));
    }
    @GetMapping("/")
    public ResponseEntity<?> getQuizzes(){
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getQuiz(@PathVariable("id") Long id){
        Quiz newQuiz =  quizService.getQuiz(id);
        if(newQuiz!=null){
            return ResponseEntity.ok(newQuiz);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz Not Found!");
    }
    @PutMapping("/")
    public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz){
        return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable("id") Long id){
        if(quizService.deleteQuiz(id)){
            return  ResponseEntity.ok("Quiz Deleted Successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz Not Found!");
    }
    @GetMapping("/results/{id}")
    public  ResponseEntity<?> getResults(@PathVariable("id") Long quizId){
        Quiz quiz = quizService.getQuiz(quizId);
        if(quiz!=null){
            return ResponseEntity.ok(quizService.getResults(quiz));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz Not Found");
    }

}
