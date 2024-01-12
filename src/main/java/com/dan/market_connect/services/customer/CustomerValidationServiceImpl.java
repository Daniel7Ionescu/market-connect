package com.dan.market_connect.services.customer;

import com.dan.market_connect.exceptions.DuplicateCustomerException;
import com.dan.market_connect.models.dtos.CustomerDTO;
import com.dan.market_connect.models.entities.Customer;
import com.dan.market_connect.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerValidationServiceImpl implements CustomerValidationService {

    private final CustomerRepository customerRepository;

    public CustomerValidationServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void validateUniqueCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.findByEmail(customerDTO.getEmail());
        if (customer != null) {
            throw new DuplicateCustomerException("Email already in use");
        }
    }
}
