package com.developer.employeemanagement.controller;


import com.developer.employeemanagement.dto.request.OperationRequestDTO;
import com.developer.employeemanagement.entity.Booking;
import com.developer.employeemanagement.entity.Guest;
import com.developer.employeemanagement.service.BookingService;
import com.developer.employeemanagement.service.GuestService;
import com.developer.employeemanagement.service.utils.DailyRateService;
import javassist.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "http://localhost:4200/")
public class BookingController {

    private final BookingService bookingService;
    private final DailyRateService dailyRateService;
    private final GuestService guestService;


    public BookingController(BookingService bookingService, DailyRateService dailyRateService, GuestService guestService) {
        this.bookingService = bookingService;
        this.dailyRateService = dailyRateService;
        this.guestService = guestService;

    }

    @GetMapping
    public List<Booking> findAll() {
        return bookingService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Booking> findById(@PathVariable("id") Long id) {
        return bookingService.findById(id);
    }

    @GetMapping("/activeGuest")
    public List<Guest> findActiveGuests() {
        return bookingService.findActiveGuests();
    }

    @GetMapping("/scheduledBookings")
    public List<Guest> findActives() {
        return bookingService.findScheduledBookings();
    }

    @PostMapping("/checkin")
    public Booking checkin(@RequestBody OperationRequestDTO operationRequestDTO) throws NotFoundException {

        try {
            Optional<Booking> optionalBooking = bookingService.findById(operationRequestDTO.getId());
            LocalDateTime checkinDate = operationRequestDTO.getCheckin();

            if (optionalBooking.isPresent()) {

                // Verifica se o horário de check-in é anterior às 14h00min
                if (checkinDate.toLocalTime().isBefore(LocalTime.of(14, 0))) {
                    throw new IllegalArgumentException("O horário para a realização do check-in deve ser após as 14h00min");
                }

                Booking booking = optionalBooking.get();
                booking.setCheckin(checkinDate);

                if (booking.isUsePark()) {
                    double parkFeeValue = dailyRateService.calculateParkFee(booking.getCheckin(), booking.getScheduledCheckoutDate());
                    booking.setParkFee(parkFeeValue);
                }

                double dailyValue = dailyRateService.calculateTotalValue(booking.getCheckin(), booking.getScheduledCheckoutDate());
                booking.setDailyValue(dailyValue);

                return bookingService.save(booking);
            } else {
                throw new NotFoundException("Reserva não encontrada para o ID: " + operationRequestDTO.getId());
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }


    @PostMapping("/checkout")
    public Booking checkout (@RequestBody OperationRequestDTO operationRequestDTO) throws NotFoundException {

        try {
            Optional<Booking> optionalBooking = bookingService.findById(operationRequestDTO.getId());
            LocalDateTime checkoutDate = operationRequestDTO.getCheckout();

            if (optionalBooking.isPresent()) {

                Booking booking = optionalBooking.get();

                if(booking.getCheckin() != null) {
                    booking.setCheckout(checkoutDate);

                    // Verifica se o horário de check-in é posterior às 12h00min
                    if (checkoutDate.toLocalTime().isAfter(LocalTime.of(12, 0))) {
                        double rate = dailyRateService.calculateCheckoutFee(checkoutDate);
                        booking.setCheckoutFee(rate);
                    }

                    if (booking.isUsePark()) {
                        double parkFeeValue = dailyRateService.calculateParkFee(booking.getCheckin(), booking.getCheckout());
                        booking.setParkFee(parkFeeValue);
                    }

                    double dailyValue = dailyRateService.calculateTotalValue(booking.getCheckin(), booking.getCheckout());
                    booking.setDailyValue(dailyValue);

                    double checkoutFee = booking.getCheckoutFee();
                    double parkFee = booking.getParkFee();
                    booking.setFinalValue(dailyValue + checkoutFee + parkFee);


                    return bookingService.save(booking);
                }else{
                    throw new Exception("Antes de realizar um checkout o checkin deve ser feito.");
                }
            } else {
                throw new NotFoundException("Reserva não encontrada para o ID: " + operationRequestDTO.getId());
            }

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PostMapping
    public Booking save(@RequestBody Booking booking) throws Exception {
        try {
            Guest guest = guestService.findById(booking.getGuest().getId())
                    .orElseThrow(() -> new Exception("Guest não encontrado com ID: " + booking.getGuest().getId()));

            //Calcula o valor das diárias com base nas datas de agendamento
            double dailyValue = dailyRateService.calculateTotalValue(booking.getScheduledCheckinDate(), booking.getScheduledCheckoutDate());
            booking.setDailyValue(dailyValue);

            if (booking.isUsePark()) {
                double parkFeeValue = dailyRateService.calculateParkFee(booking.getScheduledCheckinDate(), booking.getScheduledCheckoutDate());
                booking.setParkFee(parkFeeValue);
            }

            return bookingService.save(booking);

        } catch (Exception e) {
            throw new Exception("Algo deu errado");

        }
    }


    @PutMapping
    public Booking update(@RequestBody Booking booking) {
        return bookingService.update(booking);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        bookingService.delete(id);
    }


}
