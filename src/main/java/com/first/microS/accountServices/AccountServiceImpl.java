package com.first.microS.accountServices;

import com.first.microS.constants.AccountConstants;
import com.first.microS.dto.CustomerDto;
import com.first.microS.entity.Accounts;
import com.first.microS.entity.Customer;
import com.first.microS.exception.CustomerAlreadyExist;
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
        Optional<Customer> oldCustomer = customerRepository.findByCustomerPhone(customer.getCustomerPhone());
        if (oldCustomer.isPresent()) {
            throw new CustomerAlreadyExist("Customer already exists with Phone Number: "
                    + customer.getCustomerPhone() + "!...");
        }
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
        accounts.setCust_id(customer.getCustomerId());
        long accountNo = 100000000L + new Random().nextInt(900000000);

        accounts.setAccount_no(accountNo);
        accounts.setAccount_type(AccountConstants.SAVINGS);
        accounts.setAccount_status("Active");
        accounts.setBranch_name("Main Branch");
        accounts.setBranch_address(AccountConstants.ADDRESS);
        return accounts;
    }
}
