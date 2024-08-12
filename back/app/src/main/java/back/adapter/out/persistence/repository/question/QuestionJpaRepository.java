package back.adapter.out.persistence.repository.question;

import back.adapter.out.persistence.entity.question.QuestionEntity;
import back.domain.model.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionJpaRepository extends JpaRepository<QuestionEntity, Long> {

    @Query("SELECT q FROM QuestionEntity q WHERE q.userName = :name")
    Optional<List<QuestionEntity>> findQuestionsByUserName(@Param("name") String name);
}
