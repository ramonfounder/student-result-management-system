package io.shyftlabs.srms.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultRequestDTO {

    private Long id;

    @NotNull(message = "Course Id cannot be null")
    private Long courseId;

    @NotNull(message = "Student Id cannot be null")
    private Long studentId;

    @NotBlank(message = "Score cannot be blank")
    @Pattern(regexp = "[ABCDEF]", message = "Score must be one of the following values: A, B, C, D, E, F")
    private String score;

}

