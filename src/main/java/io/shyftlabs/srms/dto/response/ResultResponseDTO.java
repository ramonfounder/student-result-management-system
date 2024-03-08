package io.shyftlabs.srms.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponseDTO {

    private Long id;

    private String courseName;

    private String studentName;

    private String score;

}

