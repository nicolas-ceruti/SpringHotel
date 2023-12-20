package com.developer.employeemanagement.service;

import com.developer.employeemanagement.entity.Guest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class GuestServiceTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private GuestService guestService;



    @Test
    @Transactional
    @DisplayName("Espera encontrar o nome ")
    void filter() {
        Guest guest = new Guest("Nicolas", "12323253450", "479123129843");
        this.entityManager.persist(guest);

        List<Guest> listGuest = this.guestService.filter("name", "Nicolas");
        assertThat(listGuest.get(0).getName()).isEqualTo("Nicolas");
    }

    @Test
    @Transactional
    @DisplayName("Espera encontrar dois registros")
    void filterDocument() {
        Guest guest1 = new Guest("Guest1", "1234567890", "479123129843");
        this.entityManager.persist(guest1);

        Guest guest2 = new Guest("Guest2", "9012345678", "479122345843");
        this.entityManager.persist(guest2);

        Guest guest3 = new Guest("Guest3", "0000000000", "479120856843");
        this.entityManager.persist(guest3);

        List<Guest> listGuest = this.guestService.filter("document", "456");
        assertThat(listGuest.size()).isEqualTo(2);
    }
}