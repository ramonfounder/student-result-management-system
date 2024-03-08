package io.shyftlabs.srms.dto.response;


import io.shyftlabs.srms.domain.Result;
import lombok.Data;

@Data
public class ResultResponseDTO {

    private Long id;

    private String courseName;

    private String studentName;

    private String score;

    public ResultResponseDTO(Result result) {
        this.id = result.getId();
        this.courseName = result.getCourse().getCourseName();
        this.studentName = result.getStudent().getFirstName() + " " + result.getStudent().getFamilyName();
        this.score = String.valueOf(result.getScore());
    }

}

