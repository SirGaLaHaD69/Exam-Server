package com.exam.controllers.portal;


import com.exam.models.portal.Subject;
import com.exam.services.portal.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subject")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/")
    public ResponseEntity<?> addSubject(@RequestBody Subject subject){
            return ResponseEntity.ok(subjectService.addSubject(subject));
    }
    @GetMapping("/")
    public ResponseEntity<?> getSubjects(){
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }
    @GetMapping("/{code}")
    public ResponseEntity<?> getSubject(@PathVariable("code") Long subjectCode){
        Subject newSubject =  subjectService.getSubject(subjectCode);
        if(newSubject!=null){
            return ResponseEntity.ok(newSubject);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found!");
    }
    @PutMapping("/")
    public ResponseEntity<?> updateSubject(@RequestBody Subject subject){
        return ResponseEntity.ok(this.subjectService.updateSubject(subject));
    }
    @DeleteMapping("/{code}")
    public ResponseEntity<?> deleteSubject(@PathVariable("code") Long subjectCode){
        if(subjectService.deleteSubject(subjectCode)){
            return  ResponseEntity.ok("User Deleted Successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found!");
    }

}
