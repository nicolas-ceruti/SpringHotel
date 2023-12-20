package com.developer.employeemanagement.service;

import com.developer.employeemanagement.entity.Booking;
import com.developer.employeemanagement.entity.Guest;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    List<Booking> findAll();
    List<Guest> findActiveGuests();
    List<Guest> findScheduledBookings();
    Optional<Booking> findById(Long id);
    Booking save(Booking booking);
    Booking update(Booking bookin);
    void delete(Long id);

}
