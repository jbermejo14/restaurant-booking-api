package com.example.restaurantreservationaa;

import com.example.restaurantreservationaa.domain.Customer;
import com.example.restaurantreservationaa.domain.dto.customer.CustomerInDto;
import com.example.restaurantreservationaa.domain.dto.customer.CustomerOutDto;
import com.example.restaurantreservationaa.domain.dto.customer.CustomerRegistrationDto;
import com.example.restaurantreservationaa.exception.CustomerNotFoundException;
import com.example.restaurantreservationaa.repository.CustomerRepository;
import com.example.restaurantreservationaa.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTests {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testGetAll() {
        List<Customer> mockCustomerList = List.of(
                new Customer(31, "Pablo", "test@email.com", "111111111", "test", "user", Date.valueOf(LocalDate.now())),
                new Customer(32, "Juan", "test2@email.com", "222222222", "test", "user", Date.valueOf(LocalDate.now())),
                new Customer(33, "Manuel", "test3@email.com", "333333333", "test", "user", Date.valueOf(LocalDate.now()))
        );
        List<CustomerOutDto> mockCustomerOutDtoList = List.of(
                new CustomerOutDto(31, "Pablo", "test@email.com","111111111", "test", "user", Date.valueOf(LocalDate.now())),
                new CustomerOutDto(32, "Juan", "test2@email.com", "222222222", "test", "user", Date.valueOf(LocalDate.now())),
                new CustomerOutDto(33, "Manuel", "test3@email.com","333333333", "test", "user", Date.valueOf(LocalDate.now()))
        );
        when(customerRepository.findAll()).thenReturn(mockCustomerList);
        when(modelMapper.map(mockCustomerList, new TypeToken<List<CustomerOutDto>>() {}.getType())).thenReturn(mockCustomerOutDtoList);

        List<CustomerOutDto> customerList = customerService.getAll(null, null);
        assertEquals(3, customerList.size());
        assertEquals("Pablo", customerList.get(0).getName());
        assertEquals("test@email.com", customerList.get(0).getEmail());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void testGetCustomerById() throws CustomerNotFoundException {
        Customer mockCustomer = new Customer(31, "Pablo", "test@email.com", "111111111", "test", "user", Date.valueOf(LocalDate.now()));
        when(customerRepository.findById(31L)).thenReturn(Optional.of(mockCustomer));

        Customer customer = customerService.get(31);
        assertEquals("Pablo", customer.getName());
        verify(customerRepository, times(1)).findById(31L);
    }

    @Test
    public void testGetCustomerByIdNotFound() {
        when(customerRepository.findById(31L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.get(31));
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    public void testAdd() {
        CustomerRegistrationDto customerRegistrationDto = new CustomerRegistrationDto("Paco", "test4@email.com", "4444444444", "test", "user", Date.valueOf(LocalDate.now()));

        Customer mockCustomer = new Customer(0, "Paco", "test4@email.com", "4444444444", "test", "user", Date.valueOf(LocalDate.now()));
        CustomerOutDto mockCustomerOutDto = new CustomerOutDto(34, "Paco", "test4@email.com", "4444444444", "test", "user", Date.valueOf(LocalDate.now()));

        when(modelMapper.map(customerRegistrationDto, Customer.class)).thenReturn(mockCustomer);
        when(modelMapper.map(mockCustomer, CustomerOutDto.class)).thenReturn(mockCustomerOutDto);

        customerService.add(customerRegistrationDto);

        assertEquals(34, mockCustomerOutDto.getId());
        assertEquals("Paco", mockCustomerOutDto.getName());
        assertEquals("test4@email.com", mockCustomerOutDto.getEmail());

        verify(customerRepository, times(1)).save(mockCustomer);
    }
//
//    @Test
//    public void testModifyCustomer() throws CustomerNotFoundException {
//        Customer mockCustomer = new Customer(31, "Pablo", "test@email.com", "111111111", "test", "user", Date.valueOf(LocalDate.now()));
//        CustomerInDto customerInDto = new CustomerInDto("Juan", "test@email.com", "111111111", "test", "user", Date.valueOf(LocalDate.now()));
//        when(customerRepository.findById(31L)).thenReturn(Optional.of(mockCustomer));
//
//        CustomerOutDto updatedCustomer = customerService.modify(31L, customerInDto);
//        assertEquals("Juan", updatedCustomer.getName());
//        verify(customerRepository, times(1)).save(mockCustomer);
//    }

    @Test
    public void testRemoveCustomer() throws CustomerNotFoundException {
        Customer mockCustomer = new Customer(31, "Pablo", "test@email.com", "111111111", "test", "user", Date.valueOf(LocalDate.now()));
        when(customerRepository.findById(31L)).thenReturn(Optional.of(mockCustomer));

        customerService.remove(31L);
        verify(customerRepository, times(1)).deleteById(31L);
    }

    @Test
    public void testRemoveCustomerNotFound() {
        when(customerRepository.findById(31L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.remove(31L));
    }
}