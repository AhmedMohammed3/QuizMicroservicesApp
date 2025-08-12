package online.ahassan.questionservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import online.ahassan.questionservice.entities.QuestionOptions;


/**
 * DTO for {@link QuestionOptions}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionOptionRequestDto extends QuestionOptionAdminRequestDto {
    private Boolean rightAnswer;

    public static QuestionOptionRequestDto fromEntity(QuestionOptions questionOptions) {
        QuestionOptionRequestDto questionOptionRequestDto = new QuestionOptionRequestDto();
        questionOptionRequestDto.setOption(questionOptions.getOption());
        questionOptionRequestDto.setRightAnswer(questionOptions.getRightAnswer());
        return questionOptionRequestDto;
    }

    public QuestionOptions toEntity() {
        QuestionOptions questionOptions = super.toEntity();
        questionOptions.setRightAnswer(rightAnswer);
        return questionOptions;
    }

    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((rightAnswer == null) ? 0 : rightAnswer.hashCode());
        return result;
    }
}