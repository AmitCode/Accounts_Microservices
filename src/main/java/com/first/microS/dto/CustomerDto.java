package com.first.microS.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CustomerDto {
    private String CUST_NAME;
    private String CUST_ADDRESS;
    private String CUST_EMAIL;
    private String CUST_PHONE;
}
