package back.adapter.out.persistence.question;

import back.adapter.out.persistence.mapper.question.QuestionMapper;
import back.adapter.out.persistence.repository.question.QuestionJpaRepository;
import back.domain.model.question.Question;
import back.domain.port.out.QuestionRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class QuestionPersistenceAdapter implements QuestionRepository {

    private final QuestionMapper questionMapper;
    private final QuestionJpaRepository questionJpaRepository;

    public QuestionPersistenceAdapter(QuestionMapper questionMapper, QuestionJpaRepository questionJpaRepository) {
        this.questionMapper = questionMapper;
        this.questionJpaRepository = questionJpaRepository;
    }


    @Override
    public void save(Question question) {
        var qEntity = questionMapper.toEntity(question);

        questionJpaRepository.save(qEntity.get());
    }

    @Override
    public void delete(Question question) {
        var qEntity = questionMapper.toEntity(question);

        questionJpaRepository.delete(qEntity.get());
    }

    @Override
    public Optional<List<Question>> findQuestionByUserName(String userName) {
        var qsEntity = questionJpaRepository.findQuestionsByUserName(userName);

        if(qsEntity.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(qsEntity.get().stream().map(q -> questionMapper.toDomain(q).get()).toList());
    }

    @Override
    public Optional<Question> findQuestionById(Long id) {
        var qsEntity = questionJpaRepository.findById(id);

        if(qsEntity.isEmpty()){
            return Optional.empty();
        }

       return questionMapper.toDomain(qsEntity.get());
    }
}
