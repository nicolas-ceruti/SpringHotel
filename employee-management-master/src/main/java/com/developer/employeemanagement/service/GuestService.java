package com.developer.employeemanagement.service;

import com.developer.employeemanagement.entity.Guest;

import java.util.List;
import java.util.Optional;

public interface GuestService {
    List<Guest> findAll();
    List<Guest> filter(String column, String value);
    Optional<Guest> findById(Long id);
    Guest save(Guest guest);
    Guest update(Guest guest);
    void delete(Long id);

}
