package com.example.restaurantreservationaa.service;

import com.example.restaurantreservationaa.domain.Customer;
import com.example.restaurantreservationaa.domain.dto.customer.CustomerInDto;
import com.example.restaurantreservationaa.domain.dto.customer.CustomerOutDto;
import com.example.restaurantreservationaa.domain.dto.customer.CustomerRegistrationDto;
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
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " does not exist"));
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

    public CustomerOutDto modify(long customerId, CustomerInDto customerInDto) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);

        modelMapper.map(customerInDto, customer);
        customerRepository.save(customer);

        return modelMapper.map(customer, CustomerOutDto.class);
    }

    public CustomerOutDto partialUpdate(long customerId, CustomerInDto customerInDto) throws CustomerNotFoundException {
        Customer customer = get(customerId); // Retrieve the existing customer

        // Update only the fields that are present in the request
        if (customerInDto.getName() != null) {
            customer.setName(customerInDto.getName());
        }
        if (customerInDto.getEmail() != null) {
            customer.setEmail(customerInDto.getEmail());
        }
        if (customerInDto.getPhone() != null) {
            customer.setPhone(customerInDto.getPhone());
        }
        if (customerInDto.getPassword() != null) {
            customer.setPassword(customerInDto.getPassword());
        }
        if (customerInDto.getRole() != null) {
            customer.setRole(customerInDto.getRole());
        }

        // Save the updated customer
        customerRepository.save(customer);

        // Return the updated DTO
        return modelMapper.map(customer, CustomerOutDto.class);
    }

    public void remove(long customerId) throws CustomerNotFoundException {
        customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        customerRepository.deleteById(customerId);
    }

    // JPQL
    public List<Customer> getCustomersByName(String name) {
        return customerRepository.findByName(name);
    }

    public long getCustomerCountByRole(String role) {
        return customerRepository.countByRole(role);
    }

    public List<Customer> getCustomersJoinedAfter(Date date) {
        return customerRepository.findCustomersJoinedAfter(date);
    }

    // NATIVE SQL
    public List<Customer> getCustomersByNameNative(String name) {
        return customerRepository.findByNameNative(name);
    }

    public long getCustomerCountByRoleNative(String role) {
        return customerRepository.countByRoleNative(role);
    }

    public List<Customer> getCustomersJoinedAfterNative(Date date) {
        return customerRepository.findCustomersJoinedAfterNative(date);
    }
}
