package com.example.demo.service;

import com.example.demo.entity.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    Booking getBooking(Long id);
    Booking saveBooking(Booking booking, Long customerId);
    void deleteBooking(Long id);
    List<Booking> getAllBookings();
    List<Booking> getCustomerBookings(Long customerId);
    List<Booking> getBookingsByApptDate(LocalDate date);
    List<Booking> getBookingsByTimeSlot(String timeSlot);
}
