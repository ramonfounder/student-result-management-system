package io.shyftlabs.srms.dto.response;

import io.shyftlabs.srms.domain.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * CourseResponseDTO class to handle the response for Course
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDTO {

    private Long id;

    private String courseName;

    public CourseResponseDTO(Course course) {
        this.id = course.getId();
        this.courseName = course.getCourseName();
    }
}
