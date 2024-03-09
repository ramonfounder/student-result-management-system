package io.shyftlabs.srms.service;

import io.shyftlabs.srms.domain.Student;
import io.shyftlabs.srms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        this.checkAndValidateData(student);
        Student savedStudent = this.studentRepository.save(student);
        return savedStudent;
    }

    public void checkAndValidateData(Student student) {
        this.getStudentByEmailAddress(student.getEmailAddress()).ifPresent(option -> {
            throw new IllegalStateException("Student already exists");
        });
    }

    public Optional<Student> getStudentByEmailAddress(String emailAddress) {
        Example<Student> studentExample = Example.of(Student.builder().emailAddress(emailAddress).build());
        return this.studentRepository.findOne(studentExample);
    }

    public List<Student> getListStudents(String firstName, String familyName, String emailAddress) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase(true).withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Student> studentExample = Example.of(Student.builder().firstName(firstName).familyName(familyName).emailAddress(emailAddress).build(), matcher);
        return this.studentRepository.findAll(studentExample);
    }

    public void deleteStudent(Long id) {
        this.studentRepository.deleteById(id);
    }
}
