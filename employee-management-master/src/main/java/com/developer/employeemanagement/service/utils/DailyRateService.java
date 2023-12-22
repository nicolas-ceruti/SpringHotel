package com.developer.employeemanagement.service.utils;



import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DailyRateService {

    public double calculateTotalValue(LocalDateTime checkin, LocalDateTime checkout) {
        long diasDeEstadia = calculateDays(checkin, checkout);
        return calculateDailyValue(diasDeEstadia, checkin);
    }

    public double calculateParkFee(LocalDateTime checkin, LocalDateTime checkout) {
        long diasDeEstadia = calculateDays(checkin, checkout);
        return calculateParkFeeValue(diasDeEstadia, checkin);
    }

    public double calculateCheckoutFee(LocalDateTime checkout) {
        DayOfWeek checkoutDay = checkout.getDayOfWeek();
        double valorTotal = 0.0;

        if (checkoutDay == DayOfWeek.SATURDAY || checkoutDay == DayOfWeek.SUNDAY) {
            valorTotal += 90.0; // Tarifa de final de semana
        } else {
            valorTotal += 60.0; // Tarifa de segunda a sexta-feira
        }
        return valorTotal;
    }

    private long calculateDays(LocalDateTime checkin, LocalDateTime checkout) {
        LocalDate dataCheckin = checkin.toLocalDate();
        LocalDate dataCheckout = checkout.toLocalDate();

        return Duration.between(dataCheckin.atStartOfDay(), dataCheckout.atStartOfDay()).toDays();
    }

    private double calculateDailyValue(long diasDeEstadia, LocalDateTime checkin) {
        double valorTotal = 0.0;


        for (int i = 0; i < diasDeEstadia; i++) {
            LocalDate dataAtual = checkin.toLocalDate();
            DayOfWeek diaDaSemana = dataAtual.plusDays(i).getDayOfWeek();

            if (diaDaSemana == DayOfWeek.SATURDAY || diaDaSemana == DayOfWeek.SUNDAY) {
                valorTotal += 180.0; // Tarifa de final de semana
            } else {
                valorTotal += 120.0; // Tarifa de segunda a sexta-feira
            }

        }
        return valorTotal;
    }

    private double calculateParkFeeValue(long diasDeEstadia, LocalDateTime checkin) {
        double valorTotal = 0.0;

        for (int i = 0; i < diasDeEstadia; i++) {
            LocalDate dataAtual = checkin.toLocalDate();
            DayOfWeek diaDaSemana = dataAtual.plusDays(i).getDayOfWeek();

            if (diaDaSemana == DayOfWeek.SATURDAY || diaDaSemana == DayOfWeek.SUNDAY) {
                valorTotal += 20.0; // Tarifa de final de semana
            } else {
                valorTotal += 15.0; // Tarifa de segunda a sexta-feira
            }
        }

        return valorTotal;
    }
}