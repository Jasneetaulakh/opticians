package com.example.demo.customer;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
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
import com.fasterxml.jackson.databind.ObjectMapper;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Customer[] customers = new Customer[] {
            new Customer("Jon Snow", LocalDate.parse("1990-11-11"), "SL7 1FW", "3", "07707022999"),
            new Customer("Tyrion Lannister", LocalDate.parse("1981-12-12"), "SL6 1PL", "93", "07098765432")
    };

    @BeforeEach
    void setup() {
        for (Customer customer : customers) {
            customerRepository.save(customer);
        }
    }

    @AfterEach
    void clear() {
        customerRepository.deleteAll();
    }

    @Test
    void contextLoads() {
        assertNotNull(mockMvc);
    }

    @Test
    public void testGetCustomerById() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/customer/1");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Jon Snow"));

    }

    @Test
    public void testGetAllCustomers() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/customer/all");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void testValidCustomerCreation() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new Customer(
                                "Polly Molly",
                                LocalDate.parse("1991-12-13"),
                                "TW5 9BD",
                                "65",
                                "0117711717")));
        mockMvc.perform(request).andExpect(status().isCreated());
    }

    @Test
    public void testInvalidCustomerCreation() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Customer(
                        "",
                        LocalDate.parse("2000-01-01"),
                        "",
                        "",
                        "")));
        mockMvc.perform(request).andExpect(status().is4xxClientError());
    }

    @Test
    public void testCustomerNotFound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/customer/3");
        mockMvc.perform(request).andExpect(status().is4xxClientError());
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/customer/1");
        mockMvc.perform(request).andExpect(status().isNoContent());
    }
}
