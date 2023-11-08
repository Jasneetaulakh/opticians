package com.example.demo.service;

import com.example.demo.exceptions.ProfessionalStaffNotFoundException;
import com.example.demo.entity.ProfessionalStaff;
import com.example.demo.repository.ProfessionalStaffRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProfessionalStaffServiceImpl implements ProfessionalStaffService{

    ProfessionalStaffRepository professionalStaffRepository;

    @Override
    public ProfessionalStaff getProfessionalStaff(Long id) {
        Optional<ProfessionalStaff> profStaff = professionalStaffRepository.findById(id);
        return unwrapProfStaff(profStaff, id);
    }

    @Override
    public ProfessionalStaff saveProfessionalStaff(ProfessionalStaff professionalStaff) {
        return professionalStaffRepository.save(professionalStaff);
    }

    @Override
    public ProfessionalStaff updateProfessionalStaffName(Long id, String name) {
        Optional<ProfessionalStaff> profStaff = professionalStaffRepository.findById(id);
        ProfessionalStaff unwrappedProfStaff = unwrapProfStaff(profStaff, id);
        unwrappedProfStaff.setName(name);
        return professionalStaffRepository.save(unwrappedProfStaff);
    }

    @Override
    public void deleteProfessionalStaff(Long id) {
        Optional<ProfessionalStaff> profStaff = professionalStaffRepository.findById(id);
        ProfessionalStaff unwrappedProfStaff = unwrapProfStaff(profStaff, id);
        professionalStaffRepository.delete(unwrappedProfStaff);
    }

    @Override
    public List<ProfessionalStaff> getAllProfessionalStaff() {
        return (List<ProfessionalStaff>)professionalStaffRepository.findAll();
    }

    static ProfessionalStaff unwrapProfStaff(Optional<ProfessionalStaff> entity, Long id) {
        if(entity.isPresent()) {
            return entity.get();
        }
        else throw new ProfessionalStaffNotFoundException(id);
    }
}
