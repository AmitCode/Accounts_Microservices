package com.first.microS.mapperClasses;

import com.first.microS.dto.CustomerDto;
import com.first.microS.entity.Customer;

public class CustomerMapper {
    /**
     * Maps a given {@link Customer} entity to a {@link CustomerDto} dto.
     *
     * @param customer the customer entity to be mapped
     * @return the corresponding customer dto
     */
    public static CustomerDto mapEntityToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerName(customer.getCustomerName());
        customerDto.setCustomerAddress(customer.getCustomerAddress());
        customerDto.setCustomerEmail(customer.getCustomerEmail());
        customerDto.setCustomerPhone(customer.getCustomerPhone());
        return customerDto;
    }

    /**
     * Maps a given {@link CustomerDto} to a {@link Customer}.
     *
     * @param customerDto the customer dto to be mapped
     * @return the mapped customer
     */
    public static Customer mapDtoToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setCustomerAddress(customerDto.getCustomerAddress());
        System.out.println("Address: " +customer.getCustomerAddress());
        customer.setCustomerEmail(customerDto.getCustomerEmail());
        customer.setCustomerPhone(customerDto.getCustomerPhone());
        return customer;
    }
}
