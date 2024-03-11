package com.shuaibu.services;

import java.util.List;

import com.shuaibu.dto.CustomerDto;

// Service methods interface
public interface ServicesInterface {
    List<CustomerDto> getCustomers();
    CustomerDto getOneCustomer(Integer id);
    CustomerDto addCustomer(CustomerDto customer);
    CustomerDto updateCustomer(Integer id, CustomerDto customer);
    String deleteCustomer(Integer id);
}
