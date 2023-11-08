package com.example.demo.service;

import com.example.demo.exceptions.ExaminationNotFoundException;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Examination;
import com.example.demo.entity.ProfessionalStaff;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ExaminationRepository;
import com.example.demo.repository.ProfessionalStaffRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ExaminationServiceImpl implements ExaminationService{

    ExaminationRepository examinationRepository;
    CustomerRepository customerRepository;
    ProfessionalStaffRepository professionalStaffRepository;

    @Override
    public Examination getExamination(Long id) {
        Optional<Examination> examination = examinationRepository.
                findById(id);
        return unwrapExaminationWithId(examination, id);
    }

    @Override
    public Examination saveExamination(Examination examination, Long customerId, Long profStaffId) {
        Customer customer = CustomerServiceImpl.unwrapCustomer(customerRepository.findById(customerId), customerId);
        ProfessionalStaff professionalStaff = ProfessionalStaffServiceImpl.unwrapProfStaff(
                professionalStaffRepository.findById(profStaffId), profStaffId);
        examination.setCustomer(customer);
        examination.setProfessionalStaff(professionalStaff);
        return examinationRepository.save(examination);
    }

    @Override
    public void deleteExamination(Long examinationId) {
        Optional<Examination> examination = examinationRepository.findById(examinationId);
        Examination examinationUnwrapped = unwrapExaminationWithId(examination, examinationId);
        examinationRepository.delete(examinationUnwrapped);
    }

    @Override
    public List<Examination> getAllExaminations() {
        return (List<Examination>) examinationRepository.findAll();
    }

    @Override
    public List<Examination> getCustomerExaminations(Long customerId) {
        return examinationRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Examination> getProfessionalStaffExaminations(Long profStaffId) {
        return examinationRepository.findByProfessionalStaffId(profStaffId);
    }

    static Examination unwrapExamination(Optional<Examination> entity, Long customerId, Long profStaffId) {
        if(entity.isPresent()) {
            return entity.get();
        }
        else {
            throw new ExaminationNotFoundException(customerId, profStaffId);
        }
    }

    static Examination unwrapExaminationWithId(Optional<Examination> entity, Long examinationId) {
        if(entity.isPresent()) {
            return entity.get();
        }
        else {
            throw new ExaminationNotFoundException(examinationId);
        }
    }
}
