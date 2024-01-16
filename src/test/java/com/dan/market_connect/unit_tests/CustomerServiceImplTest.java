package com.dan.market_connect.unit_tests;

import com.dan.market_connect.models.dtos.CustomerDTO;
import com.dan.market_connect.models.entities.Customer;
import com.dan.market_connect.repositories.CustomerRepository;
import com.dan.market_connect.services.customer.CustomerServiceImpl;
import com.dan.market_connect.services.customer.CustomerValidationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerValidationService customerValidationService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void createCustomerTestShouldPass() {
        //given
        CustomerDTO requestCustomerDTO = CustomerDTO.builder()
                .firstName("Eric")
                .lastName("Cartman")
                .email("coolEric@gmail.com")
                .build();

        CustomerDTO responseCustomerDTO = CustomerDTO.builder()
                .id(1L)
                .firstName("Eric")
                .lastName("Cartman")
                .email("coolEric@gmail.com")
                .build();

        Customer customer = Customer.builder()
                .firstName("Eric")
                .lastName("Cartman")
                .email("coolEric@gmail.com")
                .build();

        Customer savedCustomer = Customer.builder()
                .id(1L)
                .firstName("Eric")
                .lastName("Cartman")
                .email("coolEric@gmail.com")
                .build();

        //when
        when(customerRepository.save(customer)).thenReturn(savedCustomer);
        when(modelMapper.map(requestCustomerDTO, Customer.class)).thenReturn(customer);
        when(modelMapper.map(savedCustomer, CustomerDTO.class)).thenReturn(responseCustomerDTO);

        CustomerDTO customerDTO = customerService.createCustomer(requestCustomerDTO);
        //then
        verify(customerRepository, times(1)).save(customer);
        assertNotNull(customerDTO.getId());
        assertEquals(requestCustomerDTO.getEmail(),customerDTO.getEmail());
    }
}
