package back.adapter.out.persistence.mapper.question;

import back.adapter.out.persistence.entity.product.ProductEntity;
import back.adapter.out.persistence.entity.question.QuestionEntity;
import back.domain.model.product.Product;
import back.domain.model.question.Question;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class QuestionMapper {

    public static Optional<QuestionEntity> toEntity(Question question){
        return Optional.of(new QuestionEntity(
                question.getUserName(),
                question.getQuestionContent(),
                question.getQuestionStatus()
        ));
    }

    public static Optional<Question> toDomain(QuestionEntity question){
        return Optional.of(new Question(
                question.getUserName(),
                question.getQuestionEntityContent(),
                question.getQuestionEntityStatus()
        ));
    }
    public static List<Question> questionEntityToDomainList(List<QuestionEntity> list){
        return list.stream().map(quest -> toDomain(quest).get()).toList();
    }

    public static List<QuestionEntity> questionDomainToEntityList(List<Question> list){
        return list.stream().map(quest -> toEntity(quest).get()).toList();
    }

}
