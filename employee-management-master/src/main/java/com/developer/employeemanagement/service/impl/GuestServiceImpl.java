package com.developer.employeemanagement.service.impl;

import com.developer.employeemanagement.dto.specification.GuestSpecification;
import com.developer.employeemanagement.entity.Guest;
import com.developer.employeemanagement.repository.GuestRepository;
import com.developer.employeemanagement.service.GuestService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;


    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    @Override
    public List<Guest> filter(String column, String value) {
        Specification<Guest> spec = GuestSpecification.columnEquals(column, value);
        return guestRepository.findAll(spec);

    }

    @Override
    public Optional<Guest> findById(Long id) {
        return guestRepository.findById(id);
    }

    @Override
    public Guest save(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public Guest update(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public void delete(Long id) {
        guestRepository.deleteById(id);
    }



}
