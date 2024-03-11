package com.shuaibu.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.shuaibu.dto.CustomerDto;
import com.shuaibu.models.Customer;
import com.shuaibu.services.ServicesInterface;

@WebMvcTest(controllers = CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ServicesInterface servicesInterface;

	@Autowired
	private Customer customer;
	private CustomerDto customerDto;
	private Object objectMapper;

	@BeforeEach
	public void init() {
		customer = Customer.builder().id(1).name("shuaibu").email("shuaibu@gmail.com").age(24).build();
		customerDto = CustomerDto.builder().id(1).name("shuaibu").email("shuaibu@gmail.com").age(24).build();

	}

	// @SuppressWarnings("null")
    // @Test
	// void shouldReturnDefaultMessage() throws Exception {
	// 	this.mockMvc.perform(get("/hello")).andDo(print()).andExpect(status().isOk())
	// 			.andExpect(content().string(containsString("Hello, Coders")));
	// }

	@SuppressWarnings("null")
	@Test
	public void CreateCustomer() throws Exception {
        given(servicesInterface.addCustomer(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

		ResultActions response = mockMvc.perform(post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(((ObjectMapper) objectMapper).writeValueAsString(customerDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(customerDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(customerDto.getEmail())));
    }
}