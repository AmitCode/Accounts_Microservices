package com.first.microS.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data//@Data is a shortcut for @Getter, @Setter, @EqualsAndHashCode, @ToString but
// use it where you do not require to create HashCode.but it does not contain  @AllArgsConstructor and @NoArgsConstructor
// so we have to define it explicitly in case of use.
public class AccountsDto {
    private Long account_no;
    private String account_type;
    private String account_status;
    private String branch_name;
    private String branch_address;
}
