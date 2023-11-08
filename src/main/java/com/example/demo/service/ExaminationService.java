package com.example.demo.service;

import com.example.demo.entity.Examination;

import java.util.List;

public interface ExaminationService {
    Examination getExamination(Long id);
    Examination saveExamination(Examination examination, Long customerId, Long profStaffId);
    void deleteExamination(Long examinationId);
    List<Examination> getAllExaminations();
    List<Examination> getCustomerExaminations(Long customerId);
    List<Examination> getProfessionalStaffExaminations(Long profStaffId);
}
