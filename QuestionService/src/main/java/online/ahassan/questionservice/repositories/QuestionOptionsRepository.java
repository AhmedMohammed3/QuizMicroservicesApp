package online.ahassan.questionservice.repositories;


import online.ahassan.questionservice.entities.QuestionOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionOptionsRepository extends JpaRepository<QuestionOptions, Integer> {
    Optional<QuestionOptions> findByQuestion_IdAndId(Integer questionId, Integer optionId);

}
