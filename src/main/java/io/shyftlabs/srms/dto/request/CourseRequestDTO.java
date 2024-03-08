package io.shyftlabs.srms.dto.request;

import io.shyftlabs.srms.domain.Course;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO {


    @NotBlank(message = "Course name cannot be blank")
    private String courseName;


    public Course convertToCourse() {
        return Course.builder().courseName(courseName).build();
    }
}
