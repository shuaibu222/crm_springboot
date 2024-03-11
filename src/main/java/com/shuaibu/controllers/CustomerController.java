package com.shuaibu.controllers;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shuaibu.dto.CustomerDto;
import com.shuaibu.services.CustomerServiceImplementation;

import java.util.List;


// Presentational layer(for handling request and response. it interact with service layer)
@CrossOrigin(origins = "http://localhost:3000")
@RestController
// @RequestMapping("/api/v1/customers")
public class CustomerController { 

    @Autowired
    private CustomerServiceImplementation customerServiceImplementation;

    @GetMapping("/hello")
    public String greet(){
        return "Hello, Coders";
    }

    @GetMapping("/api/v1/customers")
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        return ResponseEntity.ok(customerServiceImplementation.getCustomers());

    }

    @GetMapping("/api/v1/customers/{id}")
    public ResponseEntity<CustomerDto> getOneCustomer(@PathVariable Integer id) {
        return ResponseEntity.ok(customerServiceImplementation.getOneCustomer(id));
    }

    @PostMapping("/api/v1/customers")
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody @Valid CustomerDto customer) {
        return new ResponseEntity<>(customerServiceImplementation.addCustomer(customer), HttpStatus.CREATED);
    }


    @PutMapping("/api/v1/customers/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Integer id, @RequestBody CustomerDto customer) {
        return ResponseEntity.ok(customerServiceImplementation.updateCustomer(id, customer));
    }

    @DeleteMapping("/api/v1/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        customerServiceImplementation.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}