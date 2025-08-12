package online.ahassan.questionservice.repositories;

import online.ahassan.questionservice.entities.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>, JpaSpecificationExecutor<Question> {

    Page<Question> findAllByCategoryIgnoreCase(String category, Pageable pageable);

    Page<Question> findAllByDifficultyLevelIgnoreCase(String difficultyLevel, Pageable pageable);

    Page<Question> findAllByCategoryIgnoreCaseAndDifficultyLevelIgnoreCase(String category, String difficultyLevel, Pageable pageable);

    Page<Question> findAllByQuestionTitleContainingIgnoreCase(String questionTitle, Pageable pageable);

    @Query(value = "SELECT q FROM Question q WHERE q.category = :category ORDER BY RANDOM() LIMIT :numOfQuestions")
    List<Question> findRandomQuestionsByCategory(String category, Integer numOfQuestions);

//    Page<Question> findByQuizId(Integer quizId, Pageable pageable);
}
