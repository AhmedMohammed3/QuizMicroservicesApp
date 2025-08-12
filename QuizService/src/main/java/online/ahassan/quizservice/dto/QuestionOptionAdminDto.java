package online.ahassan.quizservice.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionOptionAdminDto implements Serializable {
    private Integer id;
    private String option;


//    public static QuestionOptionAdminDto fromEntity(QuestionOptions questionOptions) {
//        QuestionOptionAdminDto questionOptionAdminDto = new QuestionOptionAdminDto();
//        questionOptionAdminDto.setId(questionOptions.getId());
//        questionOptionAdminDto.setOption(questionOptions.getOption());
//        return questionOptionAdminDto;
//    }
//
//    public QuestionOptions toEntity() {
//        QuestionOptions questionOptions = new QuestionOptions();
//        questionOptions.setId(id);
//        questionOptions.setOption(option);
//        return questionOptions;
//}
}
