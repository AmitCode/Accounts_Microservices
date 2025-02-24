package com.first.microS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOMER_GE_INFO")
public class Customer  extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CUST_ID;
    @Column(nullable = false)
    private String CUST_NAME;
    @Column(nullable = false)
    private String CUST_ADDRESS;
    @Column(nullable = false)
    private String CUST_EMAIL;
    @Column(nullable = false)
    private String CUST_PHONE;
}
