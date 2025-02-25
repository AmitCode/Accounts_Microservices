package com.first.microS.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "ACCOUNTS_GE_INFO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Accounts extends BaseEntity{
    @Column(nullable = false)
    private Long customerId;
    @Id
    @Column(nullable = false)
    private Long account_no;
    @Column(nullable = false)
    private String account_type;
    @Column(nullable = false)
    private String account_status;
    @Column(nullable = false)
    private String branch_name;
    @Column(nullable = false)
    private String branch_address;
}
