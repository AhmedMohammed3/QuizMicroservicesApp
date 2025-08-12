package online.ahassan.questionservice.dto;

import lombok.Data;
import online.ahassan.questionservice.entities.Question;
import online.ahassan.questionservice.entities.QuestionOptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionRequestDto implements Serializable {
    private String questionTitle;
    private List<QuestionOptionRequestDto> options = new ArrayList<>();
    private String difficultyLevel;
    private String category;

    public static QuestionRequestDto fromEntity(Question question) {
        QuestionRequestDto questionRequestDto = new QuestionRequestDto();
        questionRequestDto.setQuestionTitle(question.getQuestionTitle());
        questionRequestDto.setOptions(question.getOptions().stream().map(QuestionOptionRequestDto::fromEntity).toList());
        questionRequestDto.setDifficultyLevel(question.getDifficultyLevel());
        questionRequestDto.setCategory(question.getCategory());
        return questionRequestDto;
    }

    public Question toEntity() {
        Question question = new Question();
        question.setQuestionTitle(questionTitle);
        question.setDifficultyLevel(difficultyLevel);
        question.setCategory(category);

        // Convert options and set the question reference
        List<QuestionOptions> questionOptions = options.stream()
                .map(QuestionOptionRequestDto::toEntity)
                .peek(option -> option.setQuestion(question))
                .toList();
        question.setOptions(questionOptions);

        return question;
    }
}