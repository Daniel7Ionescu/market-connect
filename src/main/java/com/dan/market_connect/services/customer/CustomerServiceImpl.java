package com.dan.market_connect.services.customer;

import com.dan.market_connect.models.dtos.CustomerDTO;
import com.dan.market_connect.models.entities.Customer;
import com.dan.market_connect.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerValidationService customerValidationService;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerValidationService customerValidationService, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.customerValidationService = customerValidationService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        customerValidationService.validateUniqueCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(modelMapper.map(customerDTO, Customer.class));
        log.info("Customer with id: {} saved in database", savedCustomer.getId());

        return modelMapper.map(savedCustomer, CustomerDTO.class);
    }
}
