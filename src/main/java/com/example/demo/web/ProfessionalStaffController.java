package com.example.demo.web;

import com.example.demo.entity.ProfessionalStaff;
import com.example.demo.service.ProfessionalStaffService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/profstaff")
public class ProfessionalStaffController {

    ProfessionalStaffService professionalStaffService;

    //(localhost:8080/profstaff/1)
    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalStaff> getProfessionalStaff(@PathVariable Long id) {
        return new ResponseEntity<>(professionalStaffService.getProfessionalStaff(id), HttpStatus.OK);
    }

    //(localhost:8080/profstaff)
    @PostMapping
    public ResponseEntity<ProfessionalStaff> saveProfessionalStaff(
            @Valid @RequestBody ProfessionalStaff professionalStaff) {
        return new ResponseEntity<>(professionalStaffService.saveProfessionalStaff(
                professionalStaff), HttpStatus.CREATED);
    }

    //(localhost:8080/profstaff/1)
    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalStaff> updateProfessionalStaffName(
            @Valid @RequestBody ProfessionalStaff professionalStaff, @PathVariable Long id) {
        return new ResponseEntity<>(
                professionalStaffService.updateProfessionalStaffName(id, professionalStaff.getName()),
                HttpStatus.OK);
    }

    //(localhost:8080/profstaff/1)
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProfessionalStaff(@PathVariable Long id) {
        professionalStaffService.deleteProfessionalStaff(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //(localhost:8080/profstaff/all)
    @GetMapping("/all")
    public ResponseEntity<List<ProfessionalStaff>> getAllProfessionalStaff() {
        return new ResponseEntity<>(professionalStaffService.getAllProfessionalStaff(), HttpStatus.OK);
    }

}
