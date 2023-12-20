package com.developer.employeemanagement.service;

import com.developer.employeemanagement.entity.Booking;
import com.developer.employeemanagement.entity.Guest;
import com.developer.employeemanagement.repository.BookingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static  org.assertj.core.api.Assertions.assertThat;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class BookingServiceTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingService bookingService;



    @Test
    @Transactional
    @DisplayName("Espera encontrar dois registros")
    void findAll() {
        Guest guest = new Guest("Nicolas", "12323253450", "479123129843");
        this.entityManager.persist(guest);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime checkin = LocalDateTime.parse("2023-12-18T12:00:00", formatter);
        LocalDateTime checkout = LocalDateTime.parse("2023-12-25T12:00:00", formatter);


        Booking booking1 = new Booking(guest, false, checkin, checkout);
        this.entityManager.persist(booking1);

        Booking booking2 = new Booking(guest, false, checkin, checkout);
        this.entityManager.persist(booking2);

        List<Booking> listBooking = this.bookingRepository.findAll();
        assertThat(listBooking.size()).isEqualTo(2);
    }



    @Test
    @Transactional
    @DisplayName("Deve retornar 1 registro de cliente ativo, mesmo que esse cliente possua duas reservas ativas")
    void findActiveGuests() {
        Guest guest = new Guest("Nicolas", "12323253450", "479123129843");
        this.entityManager.persist(guest);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime checkin = LocalDateTime.parse("2023-12-18T12:00:00", formatter);
        LocalDateTime checkout = LocalDateTime.parse("2023-12-25T12:00:00", formatter);


        Booking booking1 = new Booking(guest, false, checkin, checkout);
        booking1.setCheckin(checkin);
        this.entityManager.persist(booking1);

        Booking booking2 = new Booking(guest, false, checkin, checkout);
        booking2.setCheckin(checkin);
        this.entityManager.persist(booking2);

        List<Guest> listGuests = this.bookingService.findActiveGuests();
        assertThat(listGuests.size()).isEqualTo(1);
    }

    @Test
    @Transactional
    @DisplayName("Deve retornar 1 registro de reserva agendada")
    void findScheduledBookings() {
        Guest guest = new Guest("Nicolas", "12323253450", "479123129843");
        this.entityManager.persist(guest);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime checkin = LocalDateTime.parse("2023-12-18T12:00:00", formatter);
        LocalDateTime checkout = LocalDateTime.parse("2023-12-25T12:00:00", formatter);


        Booking booking1 = new Booking(guest, false, checkin, checkout);
        booking1.setCheckin(checkin);
        this.entityManager.persist(booking1);

        Booking booking2 = new Booking(guest, false, checkin, checkout);
        this.entityManager.persist(booking2);

        List<Guest> listGuests = this.bookingService.findScheduledBookings();
        assertThat(listGuests.size()).isEqualTo(1);
    }
}
