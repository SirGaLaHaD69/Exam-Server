package com.exam.services.portal;

import com.exam.models.portal.Student;
import com.exam.models.portal.StudentScore;
import com.exam.repos.portal.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    public Student createStudent(Student student){
        return studentRepo.save(student);
    }
    public Student getStudent(Long id){
        Optional<Student> byId = studentRepo.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        return null;
    }
    public boolean deleteStudent(Long id){
        Optional<Student> byId = studentRepo.findById(id);
        if(byId.isPresent()){
            studentRepo.delete(byId.get());
            return  true;
        }
        return false;
    }
    public Student updateStudent(Student student){
        return studentRepo.save(student);
    }


    public Set<StudentScore> getScores(Student student) {
        return student.getScores();
    }
}
