package io.shyftlabs.srms.service;

import io.shyftlabs.srms.domain.Course;
import io.shyftlabs.srms.domain.Result;
import io.shyftlabs.srms.domain.Student;
import io.shyftlabs.srms.exception.ApiException;
import io.shyftlabs.srms.repository.ResultRepository;
import io.shyftlabs.srms.shared.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * ResultService class to handle business logic for Result
 */
@Service
public class ResultService {


    private final ResultRepository resultRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    @Autowired
    public ResultService(ResultRepository resultRepository, StudentService studentService, CourseService courseService) {

        this.resultRepository = resultRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }


    /**
     * Get result by student and course from database
     */
    public Optional<Result> getResultByStudentAndCourse(Student student, Course course) {
        Example<Result> resultExample = Example.of(Result.builder().student(student).course(course).build());
        return this.resultRepository.findOne(resultExample);
    }

    /**
     * Add new result to database
     */
    public Result addNewResult(Result result) {
        Student student = this.studentService.getStudentById(result.getStudent().getId());
        Course course = this.courseService.getCourseById(result.getCourse().getId());
        this.checkAndValidateData(result);
        result.setCourse(course);
        result.setStudent(student);
        Result savedResult = this.resultRepository.save(result);
        return savedResult;
    }

    /**
     * Check and validate result data
     */
    public void checkAndValidateData(Result result) {
        this.getResultByStudentAndCourse(result.getStudent(), result.getCourse()).ifPresent(option -> {
            throw new ApiException(Messages.DUPLICATE, Map.of("description", "Result already exists"));
        });
    }

    /**
     * Get list of results from database
     */
    public List<Result> getListResults() {
        List<Result> results = this.resultRepository.findAll();
        return results;
    }

    /**
     * Delete result from database
     */
    public void deleteResult(Long id) {
        this.resultRepository.deleteById(id);
    }

}
