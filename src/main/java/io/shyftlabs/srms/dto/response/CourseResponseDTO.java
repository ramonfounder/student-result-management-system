package io.shyftlabs.srms.dto.response;

import io.shyftlabs.srms.domain.Course;
import lombok.Data;


@Data
public class CourseResponseDTO {

    private Long id;

    private String courseName;

    public CourseResponseDTO(Course course) {
        this.id = course.getId();
        this.courseName = course.getCourseName();
    }
}
