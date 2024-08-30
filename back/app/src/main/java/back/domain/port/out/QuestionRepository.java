package back.domain.port.out;

import back.domain.model.question.Question;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuestionRepository {

    void save(Question question);
    void delete(Question question);
    Optional<List<Question>> findQuestionByUserName(String userName);
    Optional<Question> findQuestionById(Long id);
    Optional<List<Question>> findQuestionsByAnnouncementId(UUID uuid);


}
