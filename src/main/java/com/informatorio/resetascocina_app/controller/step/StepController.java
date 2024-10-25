package com.informatorio.resetascocina_app.controller.step;

import com.informatorio.resetascocina_app.dto.step.StepCreateDto;
import com.informatorio.resetascocina_app.service.step.ServiceStep;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/step")
@AllArgsConstructor
public class StepController {

    private ServiceStep serviceStep;

    @GetMapping("/test")
    public ResponseEntity<?> testCatetory(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Ok Test");
    }

    @PostMapping()
    public ResponseEntity<?> newStep(@Valid @RequestBody StepCreateDto stepCreateDto){
        StepCreateDto stepCreateDto1 = serviceStep.newStep(stepCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(stepCreateDto1);
    }
}
