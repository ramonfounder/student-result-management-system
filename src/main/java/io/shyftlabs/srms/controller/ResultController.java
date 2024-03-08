package io.shyftlabs.srms.controller;

import io.shyftlabs.srms.domain.Result;
import io.shyftlabs.srms.dto.request.ResultRequestDTO;
import io.shyftlabs.srms.dto.response.ResultResponseDTO;
import io.shyftlabs.srms.service.ResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {


    private final ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }


    @PostMapping("/add")
    public ResponseEntity<ResultResponseDTO> addNewResult(@Valid @RequestBody ResultRequestDTO resultRequestDTO) {
        Result convertedToResult = resultRequestDTO.convertToResult();
        Result addedNewResult = this.resultService.addNewResult(convertedToResult);
        ResultResponseDTO resultResponseDTO = new ResultResponseDTO(addedNewResult);
        return ResponseEntity.ok(resultResponseDTO);
    }


    @GetMapping("/list")
    public ResponseEntity<List<ResultResponseDTO>> getListResults() {
        List<Result> results = this.resultService.getListResults();
        List<ResultResponseDTO> resultResponseDTOList = results.stream().map(ResultResponseDTO::new).toList();
        return ResponseEntity.ok(resultResponseDTOList);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Long id) {
        this.resultService.deleteResult(id);
        return ResponseEntity.ok().build();
    }

}
