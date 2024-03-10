package io.shyftlabs.srms;


import io.shyftlabs.srms.domain.Student;
import io.shyftlabs.srms.dto.request.StudentRequestDTO;
import io.shyftlabs.srms.dto.response.StudentResponseDTO;
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
 * StudentControllerTest class to handle the tests for StudentController¡
 */
public class StudentControllerTest extends AbstractTest {

    @Autowired
    private StudentRepository studentRepository;

    /**
     * Test to add a student with invalid input
     */
    @Test
    public void GivenInvalidInput_WhenAddStudent_ThenGetError() throws Exception {
        String request = this.objectMapper.writeValueAsString(StudentRequestDTO.builder().firstName("").familyName("").emailAddress("").build());
        this.mockMvc.perform(post("/api/students/add").contentType(MediaType.APPLICATION_JSON).content(request)).andDo(print()).andExpect(status().isBadRequest()).andExpect(jsonPath("$.type").value(Response.Type.FAILURE.name())).andExpect(jsonPath("$.message.key").value(Messages.VALIDATION));
    }

    /**
     * Test to add a student with valid input
     */
    @Test
    public void GivenValidInput_WhenAddStudent_ThenCreateStudent() throws Exception {
        String firstname = "Student1";
        String familyName = "Student family 1";
        String emailAddress = "Student1@gmail.com";
        LocalDate localDate = LocalDate.of(1990, 1, 1);
        Date dateOfBirth = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        String request = this.objectMapper.writeValueAsString(StudentRequestDTO
                .builder()
                .firstName(firstname)
                .familyName(familyName)
                .emailAddress(emailAddress)
                .dateOfBirth(dateOfBirth).build());
        MvcResult result = this.mockMvc.perform(post("/api/students/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.emailAddress")
                        .value(emailAddress)).andReturn();
        String response = result.getResponse().getContentAsString();
        StudentResponseDTO dto = this.objectMapper.readValue(response, StudentResponseDTO.class);
        Student student = this.studentRepository.findById(dto.getId()).orElse(null);
        assertNotNull(student);
        assertEquals(student.getEmailAddress(), emailAddress);
    }

}