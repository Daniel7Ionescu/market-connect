package com.dan.market_connect.services.customer;

import com.dan.market_connect.models.dtos.CustomerDTO;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerDTO customerDTO);
}
