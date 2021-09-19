package com.exam.services.portal;


import com.exam.models.portal.Quiz;
import com.exam.models.portal.Subject;
import com.exam.repos.portal.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    public Quiz getQuiz(Long id){
        Optional<Quiz> byId = quizRepository.findById(id);
        if(byId.isPresent()){

            return byId.get();
        }
        return null;
    }
    public Set<Quiz> getAllQuizzes(){
        return new LinkedHashSet<>(quizRepository.findAll());
    }
    public Quiz addQuiz(Quiz q){
        return  quizRepository.save(q);
    }
    public Quiz updateQuiz(Quiz q){
        return  quizRepository.save(q);
    }
    public boolean deleteQuiz(Long id){
        Optional<Quiz> byId = quizRepository.findById(id);
        if(byId.isPresent()){
            quizRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
