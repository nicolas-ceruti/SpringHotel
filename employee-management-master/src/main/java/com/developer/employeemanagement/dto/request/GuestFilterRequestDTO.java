package com.developer.employeemanagement.dto.request;

import java.io.Serializable;

public class GuestFilterRequestDTO implements Serializable {
    private String column;
    private String value;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
