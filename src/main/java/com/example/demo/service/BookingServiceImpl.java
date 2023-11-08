package com.example.demo.service;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Customer;
import com.example.demo.exceptions.BookingNotFoundException;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookingServiceImpl implements BookingService{

    BookingRepository bookingRepository;
    CustomerRepository customerRepository;

    @Override
    public Booking getBooking(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return unwrapBooking(booking, id);
    }

    @Override
    public List<Booking> getCustomerBookings(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingsByApptDate(LocalDate date) {
        return bookingRepository.findByApptDate(date);
    }

    @Override
    public List<Booking> getBookingsByTimeSlot(String timeSlot) {
        return bookingRepository.findByTimeSlot(timeSlot);
    }

    @Override
    public Booking saveBooking(Booking booking, Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        Customer unwrappedCustomer = CustomerServiceImpl.unwrapCustomer(customer, customerId);
        booking.setCustomer(unwrappedCustomer);
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        Booking unwrappedBooking = unwrapBooking(booking, id);
        bookingRepository.delete(unwrappedBooking);
    }

    @Override
    public List<Booking> getAllBookings() {
        return (List<Booking>)bookingRepository.findAll();
    }

    static Booking unwrapBooking(Optional<Booking> entity, Long id) {
        if(entity.isPresent()) {
            return entity.get();
        } else throw new BookingNotFoundException(id);
    }
}
