package online.ahassan.quizservice.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuizResponse implements Serializable {
    private Integer quizId;
    private Integer totalMarks;
    private Integer numberOfQuestions;
}
