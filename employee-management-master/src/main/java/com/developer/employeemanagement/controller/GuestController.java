package com.developer.employeemanagement.controller;


import com.developer.employeemanagement.dto.request.GuestFilterRequestDTO;
import com.developer.employeemanagement.entity.Guest;
import com.developer.employeemanagement.service.GuestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/guest")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public List<Guest> findAll() {
        return guestService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Guest> findById(@PathVariable("id") Long id) {
        return guestService.findById(id);
    }

    @PostMapping
    public Guest save(@RequestBody Guest guest) {
        return guestService.save(guest);
    }



    @PostMapping("/filter")
    public List filterColumn(@RequestBody GuestFilterRequestDTO guestFilterRequest) {
        String column = guestFilterRequest.getColumn();
        String value = guestFilterRequest.getValue();

        List<Guest> filteredGuests = guestService.filter(column, value);

        return filteredGuests;
    }


    @PutMapping
    public Guest update(@RequestBody Guest guest) {
        return guestService.update(guest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        guestService.delete(id);
    }


}
