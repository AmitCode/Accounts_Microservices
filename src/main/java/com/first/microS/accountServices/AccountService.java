package com.first.microS.accountServices;

import com.first.microS.dto.CustomerDto;

public interface AccountService {

    /**
     * Creates a new account for the given customer.
     *
     * @param customerDto the customer for whom the account will be created
     */
    public void createAccount(CustomerDto customerDto);
    public CustomerDto getCustomerDtsByPhone(String customerPhone);
    public boolean updateAccount(CustomerDto customerDto);
}
