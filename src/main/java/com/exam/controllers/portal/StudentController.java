package com.exam.controllers.portal;


import com.exam.models.portal.Student;
import com.exam.services.portal.StudentScoreService;
import com.exam.services.portal.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable("id") Long id){
        Student student =  studentService.getStudent(id);
        if(student!=null) {
            return  ResponseEntity.ok(student);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not found");
    }
    @GetMapping("/scores/{userId}")
    public  ResponseEntity<?> getStudentScores(@PathVariable("userId") Long userId){
        Student student =  studentService.getStudent(userId);
        if(student==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not found");
        }
        return ResponseEntity.ok(studentService.getScores(student));
    }
}
