package io.shyftlabs.srms.controller;


import io.shyftlabs.srms.domain.Student;
import io.shyftlabs.srms.dto.request.StudentRequestDTO;
import io.shyftlabs.srms.dto.response.StudentResponseDTO;
import io.shyftlabs.srms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {


    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public ResponseEntity<StudentResponseDTO> addNewStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        Student newStudent = studentRequestDTO.convertToStudent();
        Student addedNewStudent = this.studentService.addNewStudent(newStudent);
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO(addedNewStudent);
        return ResponseEntity.ok(studentResponseDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<List<StudentResponseDTO>> getListStudents(@RequestParam(required = false) String firstName,
                                                                    @RequestParam(required = false) String familyName) {
        List<Student> students = this.studentService.getListStudents(firstName, familyName);
        List<StudentResponseDTO> studentResponseDTOList = students.stream().map(StudentResponseDTO::new).toList();
        return ResponseEntity.ok(studentResponseDTOList);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        this.studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

}
