package com.exam.services.portal;


import com.exam.models.portal.Question;
import com.exam.models.portal.Quiz;
import com.exam.models.portal.Subject;
import com.exam.repos.portal.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public Question getQuestion(Long id){

        Optional<Question> byId = questionRepository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        return null;
    }
//    public Set<Question> getAllQuestions(Quiz quiz){
//        return questionRepository.findByQuiz(quiz);
//    }

    public Question addQuestion(Question question){
        return  questionRepository.save(question);
    }
    public Question updateQuestion(Question q){
        return  questionRepository.save(q);
    }
    public boolean deleteQuestion(Long id){
        Optional<Question> byId = questionRepository.findById(id);
        if(byId.isPresent()){
            questionRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public Set<Question> getAllQuestions(){
        return new LinkedHashSet<>(questionRepository.findAll());
    }
}
