package back.domain.enums;

public enum QuestionStatus {
    ANSWERED("a"),
    NOTANSWERED("n");

    private String status;

    private QuestionStatus(String status){
        this.status = status;
    }
}
