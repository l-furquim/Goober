package back.domain.model.question;

import back.domain.enums.QuestionStatus;
import back.domain.exception.QuestionException;
import jakarta.persistence.*;

import java.util.List;


public class Question {


    private String userName;


    private String questionContent;


    private Long questionId;


    private QuestionStatus questionStatus;


    List<Question> awnswers;

    public Question(){

    }

    public Question(String userName, String questionContent, QuestionStatus questionStatus) {
        this.userName = userName;
        this.questionContent = questionContent;
        this.questionStatus = questionStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public static void validate(Question question){
        if(question.getQuestionContent().isEmpty()){
            throw new QuestionException("Voce nao pode fazer uma pergunta em branco!");
        }
    }

    public List<Question> getAwnswers() {
        return awnswers;
    }

    public void setAwnswers(List<Question> awnswers) {
        this.awnswers = awnswers;
    }

    public QuestionStatus getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(QuestionStatus questionStatus) {
        this.questionStatus = questionStatus;
    }
}
