package com.example.demo.web;


import com.example.demo.entity.Examination;
import com.example.demo.service.ExaminationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/examination")
public class ExaminationController {

    ExaminationService examinationService;

    //(localhost:8080/examination/1)
    @GetMapping("/{id}")
    public ResponseEntity<Examination> getExamination(@PathVariable Long id) {
        Examination examination = examinationService.getExamination(id);
        return new ResponseEntity<>(examination, HttpStatus.OK);
    }

    //(localhost:8080/examination/customer/1)
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Examination>> getCustomerExaminations(@PathVariable Long customerId){
        return new ResponseEntity<>(examinationService.getCustomerExaminations(customerId), HttpStatus.OK);
    }

    //(localhost:8080/examination/profstaff/1)
    @GetMapping("/profstaff/{profStaffId}")
    public ResponseEntity<List<Examination>> getProfStaffExaminations(@PathVariable Long profStaffId) {
        return new ResponseEntity<>(examinationService.getProfessionalStaffExaminations(profStaffId), HttpStatus.OK);
    }

    //(localhost:8080/examination/customer/1/profstaff/1)
    @PostMapping("/customer/{customerId}/profstaff/{profStaffId}")
    public ResponseEntity<Examination> saveExamination(
            @RequestBody Examination examination, @PathVariable Long customerId, @PathVariable Long profStaffId) {
        return new ResponseEntity<>(
                examinationService.saveExamination(examination, customerId, profStaffId), HttpStatus.CREATED);
    }

    //(localhost:8080/examination/2)
    @DeleteMapping("/{examinationId}")
    public ResponseEntity<HttpStatus> deleteExamination(@PathVariable Long examinationId) {
        examinationService.deleteExamination(examinationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //(localhost:8080/examination/all)
    @GetMapping("/all")
    public ResponseEntity<List<Examination>> getAllExamination() {
        return new ResponseEntity<>(examinationService.getAllExaminations(), HttpStatus.OK);
    }
}
