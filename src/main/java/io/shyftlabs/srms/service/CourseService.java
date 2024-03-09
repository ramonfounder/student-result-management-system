package io.shyftlabs.srms.service;

import io.shyftlabs.srms.domain.Course;
import io.shyftlabs.srms.exception.ApiException;
import io.shyftlabs.srms.repository.CourseRepository;
import io.shyftlabs.srms.shared.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * CourseService class to handle business logic for Course
 */
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    /**
     * Get course by id from database
     */
    public Course getCourseById(Long id) {
        return this.courseRepository.findById(id)
                .orElseThrow(() -> new ApiException(Messages.NOT_FOUND, Map.of("description", "Course not found")));
    }

    /**
     * Add new course to database
     */
    public Course addNewCourse(Course course) {
        this.checkAndValidateData(course);
        Course savedCourse = this.courseRepository.save(course);
        return savedCourse;
    }

    /**
     * Check and validate course data
     */
    public void checkAndValidateData(Course course) {
        this.getCourseByCourseName(course.getCourseName()).ifPresent(option -> {
            throw new ApiException(Messages.DUPLICATE, Map.of("description", "Course name already exists"));
        });
    }

    /**
     * Get course by course name from database
     */
    public Optional<Course> getCourseByCourseName(String courseName) {
        Example<Course> courseExample = Example.of(Course.builder().courseName(courseName).build());
        return this.courseRepository.findOne(courseExample);
    }

    /**
     * Get list of courses from database
     */
    public List<Course> getListCourses(String courseName) {
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // Use CONTAINING for "like" behavior
                .withIgnoreCase(true);
        Example<Course> courseExample = Example.of(Course.builder().courseName(courseName).build(), matcher);
        List<Course> courses = this.courseRepository.findAll(courseExample);
        return courses;
    }

    /**
     * Delete course from database
     */
    public void deleteCourse(Long id) {
        this.courseRepository.deleteById(id);
    }
}
