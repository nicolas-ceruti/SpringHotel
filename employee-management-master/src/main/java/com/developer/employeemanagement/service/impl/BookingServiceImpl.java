package com.developer.employeemanagement.service.impl;

import com.developer.employeemanagement.dto.specification.BookingSpecification;
import com.developer.employeemanagement.entity.Booking;
import com.developer.employeemanagement.entity.Guest;
import com.developer.employeemanagement.repository.BookingRepository;
import com.developer.employeemanagement.service.BookingService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;


    public BookingServiceImpl(BookingRepository guestRepository) {
        this.bookingRepository = guestRepository;
    }

    @Override
    public List<Booking> findAll() {
        List<Booking>  bookings =  bookingRepository.findAll();
        // Ordenar a lista pelo ID
        bookings.sort(Comparator.comparing(Booking::getId).reversed());
        return bookings;
    }

    public List<Guest> findActiveGuests() {
        Specification<Booking> spec = BookingSpecification.activeGuests();
        return bookingRepository.findAll(spec)
                .stream()
                .map(Booking::getGuest)
                .distinct()
                .collect(Collectors.toList());

    }

    public List<Guest> findScheduledBookings() {
        Specification<Booking> spec = BookingSpecification.scheduledBooking();
        return bookingRepository.findAll(spec)
                .stream()
                .map(Booking::getGuest)
                .distinct()
                .collect(Collectors.toList());
    }


    @Override
    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking update(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }



}
