package io.shyftlabs.srms.service;

import io.shyftlabs.srms.domain.Student;
import io.shyftlabs.srms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {


    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudentById(Long id) {
        return this.studentRepository.findById(id).orElseThrow();
    }

    public Student addNewStudent(Student student) {
        Student savedStudent = this.studentRepository.save(student);
        return savedStudent;
    }

    public List<Student> getListCourses(String firstName, String familyName) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase(true).withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Student> studentExample = Example.of(Student.builder().firstName(firstName).familyName(familyName).build(), matcher);
        return this.studentRepository.findAll(studentExample);
    }
}
