package com.example.demo.service;

import com.example.demo.entity.ProfessionalStaff;

import java.util.List;

public interface ProfessionalStaffService {
    ProfessionalStaff getProfessionalStaff(Long id);
    ProfessionalStaff saveProfessionalStaff(ProfessionalStaff professionalStaff);
    ProfessionalStaff updateProfessionalStaffName(Long id, String name);
    void deleteProfessionalStaff(Long id);
    List<ProfessionalStaff> getAllProfessionalStaff();
}
