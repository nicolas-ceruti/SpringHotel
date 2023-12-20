package com.developer.employeemanagement.dto.request;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OperationRequestDTO implements Serializable {
    private LocalDateTime checkout;
    private LocalDateTime checkin;
    private Long id;

    public LocalDateTime getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDateTime checkout) {
        this.checkout = checkout;
    }

    public LocalDateTime getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDateTime checkin) {
        this.checkin = checkin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
