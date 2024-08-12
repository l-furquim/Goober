package back.adapter.out.persistence.entity.question;

import back.domain.enums.QuestionStatus;
import back.domain.exception.QuestionException;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "question")
public class QuestionEntity {

    @Column(name = "userName")
    private String userName;

    @Column(name = "questionContent")
    private String questionContent;

    @Id
    @Column(name = "questionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(name = "questionStatus")
    @Enumerated(EnumType.STRING)
    private QuestionStatus questionStatus;

    @Column(name = "announcementId")
    private Long announcementId;

    List<QuestionEntity> awnswers;

    public QuestionEntity(){

    }

    public QuestionEntity(String userName, String questionContent, QuestionStatus questionStatus) {
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

    public String getQuestionEntityContent() {
        return questionContent;
    }

    public void setQuestionEntityContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Long getQuestionEntityId() {
        return questionId;
    }

    public void setQuestionEntityId(Long questionId) {
        this.questionId = questionId;
    }

    public static void validate(QuestionEntity question){
        if(question.getQuestionEntityContent().isEmpty()){
            throw new QuestionException("Voce nao pode fazer uma pergunta em branco!");
        }
    }

    public List<QuestionEntity> getAwnswers() {
        return awnswers;
    }

    public void setAwnswers(List<QuestionEntity> awnswers) {
        this.awnswers = awnswers;
    }

    public QuestionStatus getQuestionEntityStatus() {
        return questionStatus;
    }

    public void setQuestionEntityStatus(QuestionStatus questionStatus) {
        this.questionStatus = questionStatus;
    }
}