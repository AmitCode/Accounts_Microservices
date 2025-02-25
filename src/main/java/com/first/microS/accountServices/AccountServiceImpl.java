package com.first.microS.accountServices;

import com.first.microS.constants.AccountConstants;
import com.first.microS.dto.AccountsDto;
import com.first.microS.dto.CustomerDto;
import com.first.microS.entity.Accounts;
import com.first.microS.entity.Customer;
import com.first.microS.exception.CustomerAlreadyExist;
import com.first.microS.exception.ResourceNotFound;
import com.first.microS.mapperClasses.AccountMapper;
import com.first.microS.mapperClasses.CustomerMapper;
import com.first.microS.repository.AccountsRepository;
import com.first.microS.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
//Spring does autowiring for single parameter constructor but if we have more than one
// constructor we need to use @AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private CustomerRepository customerRepository;
    private AccountsRepository accountsRepository;

    /**
     * Creates a new account for the given customer.
     * <p>
     * This method first checks if the customer already exists in the database by
     * phone number or email. If they do, a RuntimeException is thrown. If not, the
     * customer is saved to the database and a new account is created using
     * {@link #createNewAccount(Customer)}.
     *
     * @param customerDto the customer for whom the account is being created
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer = CustomerMapper.mapDtoToEntity(customerDto);
        Optional<Customer> oldCustomer = customerRepository.findByCustomerEmail(customer.getCustomerEmail());
//        Customer oldCustomer = customerRepository.findByCustomerPhone(customer.getCustomerPhone()).orElseThrow(
//                () -> new CustomerAlreadyExist("Customer already exists with Phone Number: "
//                        + customerDto.getCustomerPhone() + "!...")
//        );
        if (oldCustomer.isPresent()) {
            throw new CustomerAlreadyExist("Customer already exists with Phone Number: "
                    + customer.getCustomerPhone() + "!...");
        }
//        oldCustomer = customerRepository.findByCustomerEmail(customer.getCustomerEmail()).orElseThrow(
//                () -> new CustomerAlreadyExist("Customer already exists with Email: " + customerDto.getCustomerEmail())
//        );
        oldCustomer = customerRepository.findByCustomerEmail(customer.getCustomerEmail());
        if (oldCustomer.isPresent()) {
            throw new CustomerAlreadyExist("Customer already exists with Email: " +customer.getCustomerEmail());
        }
        Customer newCustomer = customerRepository.save(customer);
        Accounts accounts = accountsRepository.save(createNewAccount(newCustomer));
    }

    /**
     * Creates a new account with the given customer ID.
     *
     * <p>
     * The new account is assigned a unique account number, and is given a status of
     * "Active". The account type is set to "Savings", and the branch name and address
     * are set to defaults.
     *
     * @param customer the customer for whom the account is being created
     * @return the newly created account
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        long accountNo = 100000000L + new Random().nextInt(900000000);

        accounts.setAccount_no(accountNo);
        accounts.setAccount_type(AccountConstants.SAVINGS);
        accounts.setAccount_status("Active");
        accounts.setBranch_name("Main Branch");
        accounts.setBranch_address(AccountConstants.ADDRESS);
        return accounts;
    }

    public CustomerDto getCustomerDtsByPhone(String customerPhone){
        Customer customer = customerRepository.findByCustomerPhone(customerPhone).orElseThrow(
                ()-> new ResourceNotFound("Customer","Phone Number",customerPhone)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFound("Account","Customer Id",customer.getCustomerId().toString())
        );
//        if (!customer.isPresent()){
//            throw new ResourceNotFound("Customer","Phone Number",customerPhone);
//        }
        CustomerDto customerDto = CustomerMapper.mapEntityToDto(customer);
        customerDto.setAccountsDto(AccountMapper.mapEntityToDto(accounts));
        return customerDto;
    }

    public boolean updateAccount(CustomerDto customerDto){
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccount_no())
                    .orElseThrow(
                            () -> new ResourceNotFound("Account", "Account Number", accountsDto.getAccount_no().toString())
                    );

            accounts = accountsRepository.save(
                    AccountMapper.mapDtoToEntity(accountsDto)
            );


            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFound("Customer", "Customer Id", customerId.toString())
            );
            customer = customerRepository.save(CustomerMapper.mapDtoToEntity(customerDto));
        }
        return true;
    }
}
