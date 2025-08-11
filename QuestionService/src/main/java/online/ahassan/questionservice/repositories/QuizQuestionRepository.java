package online.ahassan.questionservice.repositories;

import online.ahassan.questionservice.entities.QuizQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Integer> {
    Page<QuizQuestion> findByQuizId(Integer quizId, Pageable pageable);
}
