package online.ahassan.questionservice.dbspecification;

import online.ahassan.questionservice.entities.Question;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public class QuestionSpecifications {

    public static Specification<Question> withFilters(Map<String, String> filters) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();

            filters.forEach((key, value) -> {
                if (value != null && !value.trim().isEmpty()) {
                    switch (key.toLowerCase()) {
                        case "category" ->
                                predicates.getExpressions().add(cb.equal(cb.lower(root.get("category")), value.toLowerCase()));
                        case "difficulty_level" ->
                                predicates.getExpressions().add(cb.equal(cb.lower(root.get("difficultyLevel")), value.toLowerCase()));
                        case "question_title" ->
                                predicates.getExpressions().add(cb.like(cb.lower(root.get("questionTitle")), "%" + value.toLowerCase() + "%"));
                    }
                }
            });

            return predicates;
        };
    }
}