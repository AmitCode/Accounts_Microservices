package com.first.microS.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CustomerDto {
    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private String customerPhone;
}
