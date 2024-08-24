package back.adapter.out.persistence.question;

import back.adapter.out.persistence.mapper.question.QuestionMapper;
import back.adapter.out.persistence.repository.announcement.AnnouncementJpaRepository;
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
    private final AnnouncementJpaRepository announcementJpaRepository;

    public QuestionPersistenceAdapter(QuestionMapper questionMapper, QuestionJpaRepository questionJpaRepository, AnnouncementJpaRepository announcementJpaRepository) {
        this.questionMapper = questionMapper;
        this.questionJpaRepository = questionJpaRepository;
        this.announcementJpaRepository = announcementJpaRepository;
    }

    @Override
    public void save(Question question) {
        var qEntity = questionMapper.toEntity(question);

        var announcementEntity = announcementJpaRepository.findById(question.getAnnouncementId());

        questionJpaRepository.save(qEntity.get());
        announcementEntity.get().setAnnouncementQuestions(qEntity.get());
    }

    @Override
    public void delete(Question question) {
        var qEntity = questionJpaRepository.findById(question.getQuestionId());

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
