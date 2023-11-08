package com.example.demo.repository;

import com.example.demo.entity.Examination;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ExaminationRepository extends CrudRepository<Examination, Long> {
    List<Examination> findByCustomerId(Long customerId);
    List<Examination> findByProfessionalStaffId(Long professionalStaffId);
}
