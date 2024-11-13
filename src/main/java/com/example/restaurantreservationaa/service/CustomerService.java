package com.example.restaurantreservationaa.service;

import com.example.restaurantreservationaa.domain.Customer;
import com.example.restaurantreservationaa.exception.CustomerNotFoundException;
import com.example.restaurantreservationaa.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAll() {
        List<Customer> allCustomers = customerRepository.findAll();
        return allCustomers;
    }

    public Customer get(long id) throws CustomerNotFoundException {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    public Customer add(Customer customer) {
        return customerRepository.save(customer);
    }

    public void remove(long customerId) throws CustomerNotFoundException {
        customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        customerRepository.deleteById(customerId);
    }
}
