package com.example.demo.professionalstaff;

import com.example.demo.entity.ProfessionalStaff;
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


@SpringBootTest
@AutoConfigureMockMvc
public class ProfessionalStaffTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProfessionalStaffRepository professionalStaffRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private ProfessionalStaff[] professionalStaffs = new ProfessionalStaff[] {
            new ProfessionalStaff(1L, "1234", "01-27294", "Polly Anne"),
            new ProfessionalStaff(2L, "5678", "01-27987", "Mary Perry"),
    };

    @BeforeEach
    void setup() {
        for(ProfessionalStaff ps : professionalStaffs) {
            professionalStaffRepository.save(ps);
        }
    }

    @AfterEach
    void clear() {
        professionalStaffRepository.deleteAll();
    }

    @Test
    public void testGetProfessionalStaffById() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/profstaff/1");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.staffPin").value("1234"));
    }

    @Test
    public void testGetAllProfessionalStaff() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/profstaff/all");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void testValidProfessionalStaffCreation() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/profstaff")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new ProfessionalStaff(
                        3L, "0880", "01-28776", "Mary Berry")));
        mockMvc.perform(request).andExpect(status().isCreated());
    }

    @Test
    public void testInvalidProfessionalStaffCreation() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/profstaff")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new ProfessionalStaff(
                        3L, "1234", "01-28776", "Mary Berry")));
        mockMvc.perform(request).andExpect(status().is4xxClientError());
    }

    @Test
    public void testProfessionalStaffNotFound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/profstaff/3");
        mockMvc.perform(request).andExpect(status().is4xxClientError());
    }

    @Test
    public void testDeleteProfessionalStaff() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/profstaff/1");
        mockMvc.perform(request).andExpect(status().isNoContent());
    }
}
