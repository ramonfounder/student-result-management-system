package io.shyftlabs.srms.service;

import io.shyftlabs.srms.domain.Course;
import io.shyftlabs.srms.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public Course getCourseById(Long id) {
        return this.courseRepository.findById(id).orElseThrow();
    }

    public Course addNewCourse(Course course) {
        this.checkAndValidateData(course);
        Course savedCourse = this.courseRepository.save(course);
        return savedCourse;
    }

    public void checkAndValidateData(Course course) {
        this.getCourseByCourseName(course.getCourseName()).ifPresent(option -> {
            throw new IllegalStateException("Course already exists");
        });
    }

    public Optional<Course> getCourseByCourseName(String courseName) {
        Example<Course> courseExample = Example.of(Course.builder().courseName(courseName).build());
        return this.courseRepository.findOne(courseExample);
    }

    public List<Course> getListCourses(String courseName) {
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // Use CONTAINING for "like" behavior
                .withIgnoreCase(true);
        Example<Course> courseExample = Example.of(Course.builder().courseName(courseName).build(), matcher);
        List<Course> courses = this.courseRepository.findAll(courseExample);
        return courses;
    }

    public void deleteCourse(Long id) {
        this.courseRepository.deleteById(id);
    }
}
