package online.ahassan.questionservice.dto;

import lombok.Data;
import online.ahassan.questionservice.entities.QuestionOptions;

import java.io.Serializable;

/**
 * DTO for {@link QuestionOptions}
 */
@Data
public class QuestionOptionAdminRequestDto implements Serializable {
    private String option;


    public static QuestionOptionAdminRequestDto fromEntity(QuestionOptions questionOptions) {
        QuestionOptionAdminRequestDto questionOptionAdminDto = new QuestionOptionAdminRequestDto();
        questionOptionAdminDto.setOption(questionOptions.getOption());
        return questionOptionAdminDto;
    }

    public QuestionOptions toEntity() {
        QuestionOptions questionOptions = new QuestionOptions();
        questionOptions.setOption(option);
        return questionOptions;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((option == null) ? 0 : option.hashCode());
        return result;
    }
}
