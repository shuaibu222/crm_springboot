package com.shuaibu.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import com.shuaibu.models.Customer;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @SuppressWarnings("null")
    @Test
    public void SaveCustomer() {

        // Arrange
        Customer customer = Customer.builder().id(1).name("shuaibu").email("shuaibu@gmail.com").age(24).build();

        //Act
        Customer savedCustomer = customerRepository.save(customer);

        //Assert
        Assertions.assertThat(savedCustomer).isNotNull();
        Assertions.assertThat(savedCustomer.getId()).isGreaterThan(0);
    }

    @SuppressWarnings("null")
    @Test
    public void FindCustomerById() {
        Customer customer = Customer.builder().id(1).name("shuaibu").email("shuaibu@gmail.com").age(24).build();
        customerRepository.save(customer);

        Customer foundedCustomer = customerRepository.findById(1).get();

        Assertions.assertThat(foundedCustomer).isNotNull();
    }

    @SuppressWarnings("null")
    @Test
    public void GetAllCustomers() {
        Customer customer = Customer.builder().id(1).name("shuaibu").email("shuaibu@gmail.com").age(24).build();
        Customer customer2 = Customer.builder().id(2).name("shuaibu").email("shuaibu@gmail.com").age(24).build();
        customerRepository.save(customer);
        customerRepository.save(customer2);

        List<Customer> customers = customerRepository.findAll();

        Assertions.assertThat(customers.size()).isEqualTo(2);

    }

    @SuppressWarnings("null")
    @Test
    public void updateCustomer() {
        Customer customer = Customer.builder().id(1).name("shuaibu").email("shuaibu@gmail.com").age(24).build();
        customerRepository.save(customer);

        Customer toUpdateCustomer = customerRepository.findById(customer.getId()).get();
        toUpdateCustomer.setName("Hassanah");
        toUpdateCustomer.setEmail("shuaibu@gmail.com");
        toUpdateCustomer.setAge(22);

        customerRepository.save(toUpdateCustomer);

        Assertions.assertThat(toUpdateCustomer.getName()).isEqualTo("Hassanah");
    }

    @SuppressWarnings("null")
    @Test
    public void DeleteCustomerById() {
        Customer customer = Customer.builder().id(1).name("shuaibu").email("shuaibu@gmail.com").age(24).build();
        customerRepository.save(customer);

        customerRepository.deleteById(customer.getId());
        Optional<Customer> deletedCustomer = customerRepository.findById(customer.getId());

        Assertions.assertThat(deletedCustomer).isEmpty();
    }

}
