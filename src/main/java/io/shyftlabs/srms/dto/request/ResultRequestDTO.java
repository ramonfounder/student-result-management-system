package io.shyftlabs.srms.dto.request;


import io.shyftlabs.srms.domain.Course;
import io.shyftlabs.srms.domain.Result;
import io.shyftlabs.srms.domain.Student;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ResultRequestDTO class to handle the request for creating a new result
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultRequestDTO {


    @NotNull(message = "Course Id cannot be null")
    private Long courseId;

    @NotNull(message = "Student Id cannot be null")
    private Long studentId;

    @NotBlank(message = "Score cannot be blank")
    @Pattern(regexp = "[ABCDEF]", message = "Score must be one of the following values: A, B, C, D, E, F")
    private String score;

    public Result convertToResult() {
        return Result.builder().course(Course.builder().id(this.courseId).build()).student(Student.builder().id(this.studentId).build()).score(Result.Score.valueOf(this.score)).build();
    }

}

