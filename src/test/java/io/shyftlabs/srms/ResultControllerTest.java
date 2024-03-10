package io.shyftlabs.srms;

import io.shyftlabs.srms.domain.Course;
import io.shyftlabs.srms.domain.Result;
import io.shyftlabs.srms.domain.Student;
import io.shyftlabs.srms.dto.request.ResultRequestDTO;
import io.shyftlabs.srms.dto.response.ResultResponseDTO;
import io.shyftlabs.srms.repository.CourseRepository;
import io.shyftlabs.srms.repository.ResultRepository;
import io.shyftlabs.srms.repository.StudentRepository;
import io.shyftlabs.srms.shared.Messages;
import io.shyftlabs.srms.shared.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * ResultControllerTest class to handle the tests for ResultController¡
 */
class ResultControllerTest extends AbstractTest {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;


    /**
     * Test to add a result with invalid input
     */
    @Test
    public void GivenInvalidInput_WhenAddResult_ThenGetError() throws Exception {
        String request = this.objectMapper.writeValueAsString(ResultRequestDTO.builder().studentId(1L).courseId(1L).score("X").build());
        this.mockMvc.perform(post("/api/results/add").contentType(MediaType.APPLICATION_JSON).content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.type").value(Response.Type.FAILURE.name()))
                .andExpect(jsonPath("$.message.key").value(Messages.VALIDATION));
    }

    /**
     * Test to add a result with valid input
     */
    @Test
    public void GivenValidInput_WhenAddResult_ThenCreateResult() throws Exception {
        LocalDate localDate = LocalDate.of(1990, 1, 1);
        Date dateOfBirth = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Student student = this.studentRepository.save(this.studentRepository.save(Student.builder()
                .firstName("Student2").familyName("Student family 2").emailAddress("Student2@gmail.com")
                .dateOfBirth(dateOfBirth)
                .build()));
        Course course = this.courseRepository.save(this.courseRepository.save(Course.builder().courseName("Course 2").build()));
        String request = this.objectMapper.writeValueAsString(ResultRequestDTO.builder().studentId(student.getId()).courseId(course.getId()).score("A").build());
        MvcResult mvcResult = this.mockMvc.perform(post("/api/results/add").contentType(MediaType.APPLICATION_JSON).content(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseName").value("Course 2"))
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        ResultResponseDTO dto = this.objectMapper.readValue(response, ResultResponseDTO.class);
        Result result = this.resultRepository.findById(dto.getId()).orElse(null);
        assertNotNull(result);
        assertEquals(result.getId(), result.getId());
    }

}