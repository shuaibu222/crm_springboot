package com.shuaibu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shuaibu.models.Customer;

// Data Access layer(Interacting with database)/Repository layer
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
        Optional<Customer> findByEmail(String email);
}
