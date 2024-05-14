package com.shuaibu;

import com.github.javafaker.Faker;
import com.shuaibu.models.Customer;
import com.shuaibu.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    public final static int CUSTOMER_NO = 30;
    private final CustomerRepository repository;

    @Override
    public void run(String... args) {
        var faker = new Faker(new Locale("en-US"));
        var customers = new ArrayList<Customer>();

        for (int i = 0; i < CUSTOMER_NO; i++) {
            Customer cus = Customer.builder()
                    .name(faker.name().name())
                    .email(faker.internet().emailAddress())
                    .age(faker.number().numberBetween(20, 60))
                    .build();

                    customers.add(cus);
        }

            repository.saveAll(customers);
    }
    
}
