package online.ahassan.questionservice.dto;

import lombok.Data;
import online.ahassan.questionservice.entities.QuizQuestion;

@Data
public class QuizQuestionDto {
    private Integer questionId;
    private Integer quizId;

    public static QuizQuestionDto fromEntity(QuizQuestion quizQuestion) {
        QuizQuestionDto dto = new QuizQuestionDto();
        dto.setQuestionId(quizQuestion.getQuestionId());
        dto.setQuizId(quizQuestion.getQuizId());
        return dto;
    }

    public QuizQuestion toEntity() {
        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setQuestionId(questionId);
        quizQuestion.setQuizId(quizId);
        return quizQuestion;
    }
}
