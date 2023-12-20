package com.developer.employeemanagement.dto.specification;

import com.developer.employeemanagement.entity.Booking;
import com.developer.employeemanagement.entity.Guest;
import org.springframework.data.jpa.domain.Specification;

public class BookingSpecification {

    public static Specification<Booking> activeGuests() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.and(
                    criteriaBuilder.isNotNull(root.get("checkin")),
                    criteriaBuilder.isNull(root.get("checkout"))
            );
        };
    }


    public static Specification<Booking> scheduledBooking() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.and(
                    criteriaBuilder.isNull(root.get("checkin")),
                    criteriaBuilder.isNull(root.get("checkout"))
            );
        };
    }

}