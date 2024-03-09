package io.shyftlabs.srms.dto.request;

import io.shyftlabs.srms.domain.Student;
import io.shyftlabs.srms.validation.DateOfBirthConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.After;

import java.util.Date;


/**
 * StudentRequestDTO class to handle the request for creating a new student
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDTO {


    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Family name cannot be blank")
    private String familyName;

    @Past(message = "Date of birth must be in the past")
    @DateOfBirthConstraint(message = "The student must be at least 10 years old")
    private Date dateOfBirth;

    @NotBlank(message = "Email address cannot be blank")
    @Email(message = "Email address should be valid")
    private String emailAddress;

    public Student convertToStudent() {
        return Student.builder().firstName(firstName).familyName(familyName).dateOfBirth(dateOfBirth).emailAddress(emailAddress).build();
    }

}
