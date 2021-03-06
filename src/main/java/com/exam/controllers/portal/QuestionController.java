package com.exam.controllers.portal;


import com.exam.models.portal.Question;
import com.exam.models.portal.Quiz;
import com.exam.models.portal.Subject;
import com.exam.services.portal.QuestionService;
import com.exam.services.portal.QuizService;
import com.exam.services.portal.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
@CrossOrigin
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<?> addQuestion(@RequestBody Question question){
        return ResponseEntity.ok(questionService.addQuestion(question));
    }
    @GetMapping("/")
    public ResponseEntity<?> getAllQuestions(){
        return ResponseEntity.ok(questionService.getAllQuestions());
    }
    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getAllQuestionsByQuiz(@PathVariable("qid") Long id){
        Quiz quiz =  quizService.getQuiz(id);
        if(quiz!=null)
            return ResponseEntity.ok(quiz.getQuestions());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz Not Found!");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getQuiz(@PathVariable("id") Long id){
        Question newQ =  questionService.getQuestion(id);
        if(newQ!=null){
            return ResponseEntity.ok(newQ);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question Not Found!");
    }
    @PutMapping("/")
    public ResponseEntity<?> updateQuestion(@RequestBody Question q){
        return ResponseEntity.ok(this.questionService.updateQuestion(q));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") Long id){
        if(questionService.deleteQuestion(id)){
            return  ResponseEntity.ok("Question Deleted Successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question Not Found!");
    }
    @PostMapping("/eval")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
        int nosAttempted=0,marksScored=0,nosCorrect=0;
        int noOfQuestions = questions.get(0).getQuiz().getNoOfQuestions();
        int maxMarks = questions.get(0).getQuiz().getMaxMarks();
        int mark = maxMarks/noOfQuestions;
        for( Question question : questions){
            if(question.getUserAns()!=null){
                nosAttempted++;
                if(question.getUserAns().trim().equals(question.getAns().trim())){
                    nosCorrect++;
                    marksScored+=mark;
                }
            }
        }
        Map<String, Integer> response = Map.of("nosCorrect", nosCorrect,"nosAttempted",nosAttempted,"marksScored",marksScored);
        return ResponseEntity.ok(response);
    }
}
