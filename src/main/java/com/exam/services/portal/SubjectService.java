package com.exam.services.portal;


import com.exam.models.portal.Subject;
import com.exam.repos.portal.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public Subject getSubject(Long subjectCode){
        Optional<Subject> byId = subjectRepository.findById(subjectCode);
        if(byId.isPresent()){
            return byId.get();
        }
        return null;
    }

    public Subject addSubject(Subject subject){
        return  subjectRepository.save(subject);
    }
    public Subject updateSubject(Subject subject){
           return  subjectRepository.save(subject);
    }
    public boolean deleteSubject(Long subjectCode){
        Optional<Subject> byId = subjectRepository.findById(subjectCode);
        if(byId.isPresent()){
            subjectRepository.deleteById(subjectCode);
            return true;
        }
        return false;
    }
    public Set<Subject> getAllSubjects(){
        return new LinkedHashSet<>(subjectRepository.findAll());
    }
}
