package com.example.demo.examination;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Examination;
import com.example.demo.entity.ProfessionalStaff;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ExaminationRepository;
import com.example.demo.repository.ProfessionalStaffRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class ExaminationTests {

    @Autowired
    private ExaminationRepository examinationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    ProfessionalStaffRepository professionalStaffRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private Customer[] customers = new Customer[] {
            new Customer("Jon Snow", LocalDate.parse("1990-11-11"), "SL7 1FW", "3", "07707022999"),
            new Customer("Tyrion Lannister", LocalDate.parse("1981-12-12"), "SL6 1PL", "93", "07098765432")
    };

    private ProfessionalStaff[] professionalStaffs = new ProfessionalStaff[] {
            new ProfessionalStaff(1L, "1234", "01-27294", "Polly Anne"),
            new ProfessionalStaff(2L, "5678", "01-27987", "Mary Perry"),
    };

    private Examination[] examinations = new Examination[] {
            new Examination(1L, LocalDate.parse("2021-01-01"), "history", "internal eye", "external eye", "lensmeter", "subjref", "refraction", "management", "recall", customers[0], professionalStaffs[0]),
            new Examination(2L, LocalDate.parse("2023-01-01"), "history1", "internal eye1", "external eye1", "lensmeter1", "subjref1", "refraction1", "management1", "recall1", customers[0], professionalStaffs[0]),
            new Examination(3L, LocalDate.parse("2021-12-12"), "history2", "internal eye2", "external eye2", "lensmeter2", "subjref2", "refraction2","management2", "recall2", customers[1], professionalStaffs[1])
    };

    @BeforeEach
    void setup() {
        for(Customer customer : customers) {
            customerRepository.save(customer);
        }
        for(ProfessionalStaff ps : professionalStaffs) {
            professionalStaffRepository.save(ps);
        }
        for(Examination examination : examinations) {
            examinationRepository.save(examination);
        }
    }

    @AfterEach
    void clear() {
        examinationRepository.deleteAll();
        professionalStaffRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    public void testGetExaminationById() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/examination/1");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customer.name").value("Jon Snow"));
    }

    @Test
    public void testGetExaminationByCustomerId() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/examination/customer/1");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void testGetExaminationByProfStaffId() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/examination/profstaff/1");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void testGetAllExaminations() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/examination/all");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(3));
    }

    @Test
    public void testValidExaminationCreation() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/examination/customer/2/profstaff/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Examination (4L, LocalDate.parse("2021-01-01"),
                        "history3", "internal eye3", "external eye3",
                        "lensmeter", "subjref", "refraction",
                        "management", "recall", customers[1], professionalStaffs[1])));
        mockMvc.perform(request).andExpect(status().isCreated());
    }

    @Test
    public void testDeleteExamination() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/examination/1");
        mockMvc.perform(request).andExpect(status().isNoContent());
    }

    @Test
    public void testExaminationNotFound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/examination/5");
        mockMvc.perform(request).andExpect(status().is4xxClientError());
    }
}
