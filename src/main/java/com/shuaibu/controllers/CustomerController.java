package com.shuaibu.controllers;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shuaibu.dto.CustomerDto;
import com.shuaibu.services.ServicesInterface;

import java.util.List;


// Presentational layer(for handling request and response. it interact with service layer)
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CustomerController { 

    private ServicesInterface servicesInterface;

    public CustomerController(ServicesInterface servicesInterface) {
        this.servicesInterface = servicesInterface;
    }

    @GetMapping("/hello")
    public String greet(){
        return "Hello, Coders";
    }

    @GetMapping("/api/v1/customers")
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        return ResponseEntity.ok(servicesInterface.getCustomers());

    }

    @GetMapping("/api/v1/customers/{id}")
    public ResponseEntity<CustomerDto> getOneCustomer(@PathVariable Integer id) {
        return ResponseEntity.ok(servicesInterface.getOneCustomer(id));
    }

    @PostMapping("/api/v1/customers")
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody @Valid CustomerDto customer) {
        return new ResponseEntity<>(servicesInterface.addCustomer(customer), HttpStatus.CREATED);
    }


    @PutMapping("/api/v1/customers/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Integer id, @RequestBody CustomerDto customer) {
        return ResponseEntity.ok(servicesInterface.updateCustomer(id, customer));
    }

    @DeleteMapping("/api/v1/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {
        return new ResponseEntity<>(servicesInterface.deleteCustomer(id), HttpStatus.NO_CONTENT);
    }
}