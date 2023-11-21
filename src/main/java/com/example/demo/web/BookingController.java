package com.example.demo.web;

import com.example.demo.entity.Booking;
import com.example.demo.service.BookingService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/booking")
public class BookingController {

    BookingService bookingService;

    //(localhost:8080/booking/id/1)
    @GetMapping("/id/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
        return new ResponseEntity<>(bookingService.getBooking(id), HttpStatus.OK);
    }

    //(localhost:8080/booking/customer/1)
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Booking>> getCustomerBookings(@PathVariable Long customerId) {
        return new ResponseEntity<>(bookingService.getCustomerBookings(customerId), HttpStatus.OK);
    }

    //(localhost:8080/booking/d/thisdate?date=2023-11-11)
    @GetMapping("/d/thisdate")
    public ResponseEntity<List<Booking>> getBookingsByApptDate(
            @RequestParam ("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        return new ResponseEntity<>(bookingService.getBookingsByApptDate(date), HttpStatus.OK);
    }

    //(localhost:8080/booking/time/09.30-10.00)
    @GetMapping("/time/{timeSlot}")
    public ResponseEntity<List<Booking>> getBookingsByTimeSlot(@PathVariable String timeSlot) {
        return new ResponseEntity<>(bookingService.getBookingsByTimeSlot(timeSlot), HttpStatus.OK);
    }

    //(localhost:8080/booking/customer/1)
    @PostMapping("/customer/{customerId}")
    public ResponseEntity<Booking> saveBooking(
            @Valid @RequestBody Booking booking, @PathVariable Long customerId
    ) {
        return new ResponseEntity<>(bookingService.saveBooking(booking, customerId), HttpStatus.CREATED);
    }

    //(localhost:8080/booking/1)
    @DeleteMapping("/{id}")
    public ResponseEntity<Booking> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //(localhost:8080/booking/all)
    @GetMapping("/all")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.OK);
    }

}
