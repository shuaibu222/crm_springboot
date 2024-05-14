package com.shuaibu.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuaibu.dto.CustomerDto;
import com.shuaibu.models.Customer;
import com.shuaibu.repository.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;


// Service/Business logic layer(it interacts with Data Access layer)
@Service
public class CustomerServiceImplementation implements ServicesInterface {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerDto> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();

        for (Customer customer : customers) {
            customerDtos.add(mapToDto(customer));
        }

        return customerDtos;
    }

    @Override
    public CustomerDto getOneCustomer(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer with id " + id + " not found"));

        return mapToDto(customer);
    }


    @Override
    public CustomerDto addCustomer(@Valid CustomerDto customerDto) {

        // convert to Customer entity
        Customer customer = mapToEntity(customerDto);

        // save to database
        Customer newCustomer = customerRepository.save(customer);

        // create response from customerDto
        CustomerDto customerResponse = mapToDto(newCustomer);

        return customerResponse;
    }

    @Override
    public CustomerDto updateCustomer(Integer id, CustomerDto customerDto) {

        Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));

        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setAge(customerDto.getAge());

        Customer updatedCustomer = customerRepository.save(customer);

        return mapToDto(updatedCustomer);
    }

    @Override
    public String deleteCustomer(Integer id){
        customerRepository.deleteById(id);

        return "Deleted Successfully";
    }

    // utils
    private CustomerDto mapToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setAge(customer.getAge());
        return customerDto;
    }

    private Customer mapToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setAge(customerDto.getAge());

        return customer;
    }
}
