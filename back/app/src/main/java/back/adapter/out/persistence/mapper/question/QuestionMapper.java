package back.adapter.out.persistence.mapper.question;

import back.adapter.out.persistence.entity.question.QuestionEntity;
import back.domain.model.question.Question;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class QuestionMapper {

    public Optional<QuestionEntity> toEntity(Question question){
        return Optional.of(new QuestionEntity(
                question.getUserName(),
                question.getQuestionContent(),
                question.getQuestionStatus()
        ));
    }

    public Optional<Question> toDomain(QuestionEntity question){
        return Optional.of(new Question(
                question.getUserName(),
                question.getQuestionEntityContent(),
                question.getQuestionEntityStatus()
        ));
    }

}
