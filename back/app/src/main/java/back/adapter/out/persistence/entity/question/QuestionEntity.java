package back.adapter.out.persistence.entity.question;

import back.domain.enums.QuestionStatus;
import back.domain.exception.QuestionException;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "question")
public class QuestionEntity {

    @Column(name = "user_name")
    private String userName;

    @Column(name = "question_content")
    private String questionContent;

    @Id
    @Column(name = "question_id")
    private UUID questionId;

    @Column(name = "question_status")
    @Enumerated(EnumType.STRING)
    private QuestionStatus questionStatus;

    @Column(name = "announcement_id")
    private UUID announcementId;

    @OneToMany(mappedBy = "parentQuestion", cascade = CascadeType.ALL)
    List<QuestionEntity> awnswers;

    @ManyToOne
    @JoinColumn(name = "parent_question_id")
    private QuestionEntity parentQuestion;

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

    public UUID getQuestionEntityId() {
        return questionId;
    }

    public void setQuestionEntityId(UUID questionId) {
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

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public void setQuestionId(UUID questionId) {
        this.questionId = questionId;
    }

    public QuestionStatus getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(QuestionStatus questionStatus) {
        this.questionStatus = questionStatus;
    }

    public UUID getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(UUID announcementId) {
        this.announcementId = announcementId;
    }

    public QuestionEntity getParentQuestion() {
        return parentQuestion;
    }

    public void setParentQuestion(QuestionEntity parentQuestion) {
        this.parentQuestion = parentQuestion;
    }
}