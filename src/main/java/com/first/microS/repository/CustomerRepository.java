package com.first.microS.repository;

import com.first.microS.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    /**
     * Finds a customer by their phone number.
     *
     * @param mobileNo the phone number
     * @return the customer or null if not found
     */
    public Optional<Customer> findByCustomerPhone(String mobileNo);

    /**
     * Finds a customer by their email address.
     *
     * @param email the email address
     * @return the customer or null if not found
     */
    public Optional<Customer> findByCustomerEmail(String email);
}
