package com.example.demo.repository;

import com.example.demo.entity.Booking;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findByCustomerId(Long customerId);
    List<Booking> findByApptDate(LocalDate date);
    List<Booking> findByTimeSlot(String timeSlot);
}
