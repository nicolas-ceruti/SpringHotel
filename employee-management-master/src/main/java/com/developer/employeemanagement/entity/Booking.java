package com.developer.employeemanagement.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;

    @Column(name = "checkin_date")
    private LocalDateTime checkin;

    @Column(name = "checkout_date")
    private LocalDateTime checkout;

    @Column(name = "scheduled_checkin_date", nullable = false)
    private LocalDateTime scheduledCheckinDate;

    @Column(name = "scheduled_checkout_date", nullable = false)
    private LocalDateTime scheduledCheckoutDate;

    @Column(name = "usePark")
    private boolean usePark;

    @Column(name = "park_fee")
    private double parkFee;

    @Column(name = "daily_value")
    private double dailyValue;

    @Column(name = "checkout_fee")
    private double checkoutFee;

    @Column(name = "final_value")
    private double finalValue;

    public Long getId() {
        return id;
    }

    public Booking(Guest guest, boolean usePark, LocalDateTime scheduledCheckinDate, LocalDateTime scheduledCheckoutDate) {
        this.guest = guest;
        this.usePark = usePark;
        this.scheduledCheckinDate = scheduledCheckinDate;
        this.scheduledCheckoutDate = scheduledCheckoutDate;
    }

    public Booking() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public LocalDateTime getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDateTime checkin) {
        this.checkin = checkin;
    }

    public LocalDateTime getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDateTime checkout) {
        this.checkout = checkout;
    }

    public LocalDateTime getScheduledCheckinDate() {
        return scheduledCheckinDate;
    }

    public void setScheduledCheckinDate(LocalDateTime scheduledCheckinDate) {
        this.scheduledCheckinDate = scheduledCheckinDate;
    }

    public LocalDateTime getScheduledCheckoutDate() {
        return scheduledCheckoutDate;
    }

    public void setScheduledCheckoutDate(LocalDateTime scheduledCheckoutDate) {
        this.scheduledCheckoutDate = scheduledCheckoutDate;
    }

    public boolean isUsePark() {
        return usePark;
    }

    public void setUsePark(boolean usePark) {
        this.usePark = usePark;
    }

    public double getDailyValue() {
        return dailyValue;
    }

    public void setDailyValue(double dailyValue) {
        this.dailyValue = dailyValue;
    }

    public double getParkFee() {
        return parkFee;
    }

    public void setParkFee(double parkFee) {
        this.parkFee = parkFee;
    }

    public double getCheckoutFee() {
        return checkoutFee;
    }

    public void setCheckoutFee(double checkoutFee) {
        this.checkoutFee = checkoutFee;
    }

    public double getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(double finalValue) {
        this.finalValue = finalValue;
    }
}
