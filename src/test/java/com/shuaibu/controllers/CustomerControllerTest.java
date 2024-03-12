package com.shuaibu.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.shuaibu.dto.CustomerDto;
import com.shuaibu.services.ServicesInterface;

@WebMvcTest(controllers = CustomerController.class)
@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ServicesInterface servicesInterface;

	@BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CustomerController(servicesInterface)).build();
    }

    @SuppressWarnings("null")
	@Test
    void getCustomers() throws Exception {
        // Mocking the behavior of the service layer
        List<CustomerDto> customers = new ArrayList<>();
        customers.add(new CustomerDto(1, "Shuaibu", "shuaibu@example.com", 24));
		customers.add(new CustomerDto(2, "Sadik", "sadik@example.com", 18));

        when(servicesInterface.getCustomers()).thenReturn(customers);

        // Performing GET request to "/api/v1/customers"
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers"))
                .andExpect(status().isOk()) // Expecting HTTP 200 OK status
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)); // Expecting JSON content
    }

	@SuppressWarnings("null")
	@Test
    void addCustomer() throws Exception {
        // Mocking the behavior of the service layer
        CustomerDto customerDto = new CustomerDto(1, "Shuaibu", "shuaibu@example.com", 24);
        when(servicesInterface.addCustomer(customerDto)).thenReturn(customerDto);

        // Converting CustomerDto to JSON string
        String customerJson = new ObjectMapper().writeValueAsString(customerDto);

        // Performing POST request to "/api/v1/customers" with JSON body
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson))
                .andExpect(status().isCreated()); // Expecting HTTP 201 Created status
    }

	@SuppressWarnings("null")
	@Test
	void getOneCustomer() throws Exception {
    // Mocking the behavior of the service layer
    int customerId = 1;
    CustomerDto customerDto = new CustomerDto(customerId, "Shuaibu", "shuaibu@example.com", 24);
    when(servicesInterface.getOneCustomer(customerId)).thenReturn(customerDto);

    // Performing GET request to "/api/v1/customers/{id}"
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/{id}", customerId))
            .andExpect(status().isOk()) // Expecting HTTP 200 OK status
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)) // Expecting JSON content
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(customerId)); // Verifying response contains correct customer ID
}

	@SuppressWarnings("null")
	@Test
	void updateCustomer() throws Exception {
    // Mocking the behavior of the service layer
    int customerId = 1;
    CustomerDto customerDto = new CustomerDto(customerId, "Updated Name", "updated@example.com", 20);
    when(servicesInterface.updateCustomer(customerId, customerDto)).thenReturn(customerDto);

    // Converting CustomerDto to JSON string
    String customerJson = new ObjectMapper().writeValueAsString(customerDto);

    // Performing PUT request to "/api/v1/customers/{id}" with JSON body
    mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customers/{id}", customerId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(customerJson))
            .andExpect(status().isOk()) // Expecting HTTP 200 OK status
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)) // Expecting JSON content
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Name")); // Verifying response contains updated name
}

	@Test
	void deleteCustomer() throws Exception {
		int customerId = 1;
		// Performing DELETE request to "/api/v1/customers/{id}"
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/customers/{id}", customerId))
				.andExpect(status().isNoContent()); // Expecting HTTP 204 No Content status
	}

}