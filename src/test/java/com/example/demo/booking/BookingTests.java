package com.example.demo.booking;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Customer;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.CustomerRepository;
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
public class BookingTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Customer[] customers = new Customer[] {
            new Customer("Jon Snow", LocalDate.parse("1990-11-11"), "SL7 1FW", "3", "07707022999"),
            new Customer("Tyrion Lannister", LocalDate.parse("1981-12-12"), "SL6 1PL", "93", "07098765432")
    };

    private Booking[] bookings = new Booking[] {
            new Booking(1L, LocalDate.parse("2021-11-11"), "10:15-10:45", customers[0]),
            new Booking(2L, LocalDate.parse("2023-10-10"), "15:30-16:00", customers[1]),
            new Booking(3L, LocalDate.parse("2023-10-10"), "09:30-10:00", customers[0])
            };

    @BeforeEach
    void setUp() {
        for(Customer customer : customers) {
            customerRepository.save(customer);
        }
        for(Booking booking : bookings) {
            bookingRepository.save(booking);
        }
    }

    @AfterEach
    void clear() {
        bookingRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    public void testGetBookingByBookingId() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/booking/id/1");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.timeSlot").value("10:15"));
    }

    @Test
    public void testGetBookingByCustomerId() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/booking/customer/1");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void testGetBookingByDate() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/booking/d/thisdate?date=2023-10-10");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void testGetBookingByTime() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/booking/time/09:30-10:00");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    public void testGetAllBookings() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/booking/all");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(3));
    }

    @Test
    public void testValidBookingCreation() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/booking/customer/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new Booking(
                                4L,
                                LocalDate.parse("2023-12-12"),
                                "09:00-09:30",
                                customers[1])));
        mockMvc.perform(request).andExpect(status().isCreated());
    }

    @Test
    public void testBookingNotFound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/booking/id/5");
        mockMvc.perform(request).andExpect(status().is4xxClientError());
    }

    @Test
    public void testDeleteBooking() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/booking/1");
        mockMvc.perform(request).andExpect(status().isNoContent());
    }
}
