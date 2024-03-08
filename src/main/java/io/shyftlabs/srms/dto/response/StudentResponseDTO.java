package io.shyftlabs.srms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {

    private Long id;

    private String firstName;

    private String familyName;

    private Date dateOfBirth;

    private String emailAddress;

}
