package io.shyftlabs.srms.controller;

import io.shyftlabs.srms.domain.Course;
import io.shyftlabs.srms.dto.request.CourseRequestDTO;
import io.shyftlabs.srms.dto.response.CourseResponseDTO;
import io.shyftlabs.srms.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CourseController class to handle the requests for Course
 */
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Add a new course
     */
    @PostMapping("/add")
    public ResponseEntity<CourseResponseDTO> addNewCourse(@Valid @RequestBody CourseRequestDTO courseRequestDTO) {
        Course newCourse = courseRequestDTO.convertToCourse();
        Course addedNewCourse = this.courseService.addNewCourse(newCourse);
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO(addedNewCourse);
        return ResponseEntity.ok(courseResponseDTO);
    }

    /**
     * Get a list of courses
     */
    @GetMapping("/list")
    public ResponseEntity<List<CourseResponseDTO>> getListCourses(@RequestParam(required = false) String courseName) {
        List<Course> courses = this.courseService.getListCourses(courseName);
        List<CourseResponseDTO> courseResponseDTOList = courses.stream().map(CourseResponseDTO::new).toList();
        return ResponseEntity.ok(courseResponseDTOList);
    }

    /**
     * Delete a course by id
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        this.courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }
}
