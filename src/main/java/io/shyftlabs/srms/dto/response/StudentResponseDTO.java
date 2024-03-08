package io.shyftlabs.srms.dto.response;

import io.shyftlabs.srms.domain.Student;
import lombok.Data;

import java.util.Date;


@Data
public class StudentResponseDTO {

    private Long id;

    private String firstName;

    private String familyName;

    private Date dateOfBirth;

    private String emailAddress;

    public StudentResponseDTO(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.familyName = student.getFamilyName();
        this.dateOfBirth = student.getDateOfBirth();
        this.emailAddress = student.getEmailAddress();
    }

}
