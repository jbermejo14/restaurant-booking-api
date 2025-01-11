package com.example.restaurantreservationaa.service;

import com.example.restaurantreservationaa.domain.Customer;
import com.example.restaurantreservationaa.domain.MenuItem;
import com.example.restaurantreservationaa.domain.Order;
import com.example.restaurantreservationaa.domain.dto.customer.CustomerOutDto;
import com.example.restaurantreservationaa.domain.dto.customer.CustomerRegistrationDto;
import com.example.restaurantreservationaa.domain.dto.menuitem.MenuItemOutDto;
import com.example.restaurantreservationaa.domain.dto.order.OrderOutDto;
import org.modelmapper.ModelMapper;
import com.example.restaurantreservationaa.exception.CustomerNotFoundException;
import com.example.restaurantreservationaa.repository.CustomerRepository;
import jakarta.persistence.criteria.Predicate;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;
    public List<CustomerOutDto> getAll(String name, String email) {
        List<Customer> customerList;

        if (name == null && email == null) {
            customerList = customerRepository.findAll();
        } else if (email == null) {
            customerList = customerRepository.findByName(name);
        } else if (name == null) {
            customerList = customerRepository.findByEmail(email);
        } else {
            customerList = customerRepository.findByNameAndEmail(name, email);
        }

        return modelMapper.map(customerList, new TypeToken<List<CustomerOutDto>>() {
        }.getType());
    }

    public Customer get(long id) throws CustomerNotFoundException {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    public CustomerOutDto add(CustomerRegistrationDto customerInDto) {
        Customer customer = modelMapper.map(customerInDto, Customer.class);
        Customer newCustomer = customerRepository.save(customer);

        return modelMapper.map(newCustomer, CustomerOutDto.class);
    }

    public List<Customer> getCustomersByFilter(String name, String email, String role) {
        return customerRepository.findAll((root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (name != null && !name.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            if (email != null && !email.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("email"), "%" + email + "%"));
            }
            if (role != null && !role.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("role"), "%" + role + "%"));
            }

            return predicate;
        });
    }

    public void remove(long customerId) throws CustomerNotFoundException {
        customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        customerRepository.deleteById(customerId);
    }
}
