package com.shuaibu.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shuaibu.dto.CustomerDto;
import com.shuaibu.models.Customer;
import com.shuaibu.repository.CustomerRepository;


@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplementationTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImplementation customerServiceImplementation;

    @SuppressWarnings("null")
    @Test
    void testAddCustomer() {
        Customer customer = Customer.builder().id(1).name("shuaibu").email("shuaibu@gmail.com").age(24).build();
        CustomerDto customerDto = CustomerDto.builder().id(1).name("shuaibu").email("shuaibu@gmail.com").age(24).build();

        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

        CustomerDto retrievedCustomer = customerServiceImplementation.addCustomer(customerDto);
        assertEquals("shuaibu", retrievedCustomer.getName());

    }

    @Test
void testGetAllCustomers() {
    List<Customer> customers = Arrays.asList(
        Customer.builder().id(1).name("shuaibu").email("shuaibu@gmail.com").age(24).build(),
        Customer.builder().id(2).name("shuaibu").email("shuaibu@gmail.com").age(24).build()
    );

    when(customerRepository.findAll()).thenReturn(customers);

    List<CustomerDto> retrievedCustomer = customerServiceImplementation.getCustomers();
    assertEquals(2, retrievedCustomer.size());
}


    @Test
    void testGetOneCustomer() {
        Customer customer = Customer.builder().id(1).name("shuaibu").email("shuaibu@gmail.com").age(24).build();
        when(customerRepository.findById(1)).thenReturn(Optional.ofNullable(customer));

        CustomerDto retrievedCustomer = customerServiceImplementation.getOneCustomer(1);
        assertEquals("shuaibu", retrievedCustomer.getName());

    }

    @SuppressWarnings("null")
    @Test
    void testToUpdateCustomer() {
        Customer customer = Customer.builder().id(1).name("shuaibu").email("shuaibu@gmail.com").age(24).build();
        CustomerDto customerDto = CustomerDto.builder().id(1).name("hassanah").email("hassanah@gmail.com").age(22).build();

        when(customerRepository.findById(1)).thenReturn(Optional.ofNullable(customer));
        when(customerRepository.save(customer)).thenReturn(customer);

        CustomerDto updatedCustomer = customerServiceImplementation.updateCustomer(1, customerDto);
        
        assertEquals("hassanah", updatedCustomer.getName());
        assertEquals("hassanah@gmail.com", updatedCustomer.getEmail());

    }

    @SuppressWarnings("null")
    @Test
    void testDeleteCustomer() {
    Customer customer = Customer.builder().id(1).name("shuaibu").email("shuaibu@gmail.com").age(24).build();
    doNothing().when(customerRepository).deleteById(customer.getId());

    assertAll(() -> customerServiceImplementation.deleteCustomer(customer.getId()));
}

}
