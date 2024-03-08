package io.shyftlabs.srms.service;

import io.shyftlabs.srms.domain.Course;
import io.shyftlabs.srms.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addNewCourse(Course course) {
        return null;
    }

    public List<Course> getListCourses(String courseName) {
        return null;
    }
}
