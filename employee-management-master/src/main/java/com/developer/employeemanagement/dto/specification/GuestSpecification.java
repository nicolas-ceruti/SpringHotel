package com.developer.employeemanagement.dto.specification;

import com.developer.employeemanagement.entity.Guest;
import org.springframework.data.jpa.domain.Specification;

public class GuestSpecification {

    public static Specification<Guest> columnEquals(String column, String value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(column), "%" + value + "%");
    }

}