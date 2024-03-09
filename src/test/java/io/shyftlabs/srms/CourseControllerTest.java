package io.shyftlabs.srms;

import io.shyftlabs.srms.domain.Course;
import io.shyftlabs.srms.dto.request.CourseRequestDTO;
import io.shyftlabs.srms.dto.response.CourseResponseDTO;
import io.shyftlabs.srms.repository.CourseRepository;
import io.shyftlabs.srms.shared.Messages;
import io.shyftlabs.srms.shared.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * CourseControllerTest class to handle the tests for CourseController¡
 */
public class CourseControllerTest extends AbstractTest {

    @Autowired
    private CourseRepository courseRepository;

    /**
     * Test to add a course with invalid input
     */
    @Test
    public void GivenInvalidInput_WhenAddCourse_ThenGetError() throws Exception {
        String request = this.objectMapper.writeValueAsString(CourseRequestDTO.builder().courseName("").build());
        this.mockMvc.perform(post("/api/courses/add").contentType(MediaType.APPLICATION_JSON).content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.type").value(Response.Type.FAILURE.name()))
                .andExpect(jsonPath("$.message.key").value(Messages.VALIDATION));
    }

    /**
     * Test to add a course with valid input
     */
    @Test
    public void GivenValidInput_WhenAddCourse_ThenCreateCourse() throws Exception {
        String courseName = "Test";
        String request = this.objectMapper.writeValueAsString(CourseRequestDTO.builder().courseName(courseName).build());
        MvcResult result = this.mockMvc.perform(post("/api/courses/add").contentType(MediaType.APPLICATION_JSON).content(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseName").value(courseName))
                .andReturn();
        String response = result.getResponse().getContentAsString();
        CourseResponseDTO dto = this.objectMapper.readValue(response, CourseResponseDTO.class);
        Course course = this.courseRepository.findById(dto.getId()).orElse(null);
        assertNotNull(course);
        assertEquals(course.getCourseName(), courseName);
    }

}
