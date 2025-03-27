package com.example.restaurantreservationaa;

import com.example.restaurantreservationaa.domain.Beverage;
import com.example.restaurantreservationaa.domain.MenuItem;
import com.example.restaurantreservationaa.domain.Order;
import com.example.restaurantreservationaa.domain.dto.order.OrderInDto;
import com.example.restaurantreservationaa.domain.dto.order.OrderOutDto;
import com.example.restaurantreservationaa.domain.dto.order.OrderRegistrationDto;
import com.example.restaurantreservationaa.exception.OrderNotFoundException;
import com.example.restaurantreservationaa.repository.OrderRepository;
import com.example.restaurantreservationaa.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        // Configuración inicial si es necesario
    }

    @Test
    public void testGetAll() {
        List<Order> mockOrderList = List.of(
                new Order(1, new ArrayList<>(), new ArrayList<>(), "Pending", Date.valueOf("2023-10-01"), 50.0f),
                new Order(2, new ArrayList<>(), new ArrayList<>(), "Completed", Date.valueOf("2023-10-02"), 75.0f),
                new Order(3, new ArrayList<>(), new ArrayList<>(), "Pending", Date.valueOf("2023-10-03"), 100.0f)
        );

        when(orderRepository.findAll()).thenReturn(mockOrderList);

        List<OrderOutDto> orderList = orderService.getAll(0.0f, null);
        assertEquals(3, orderList.size());
        assertEquals("Pending", orderList.get(0).getStatus());
        assertEquals("Completed", orderList.get(1).getStatus());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void testGetOrderById() throws OrderNotFoundException {
        Order mockOrder = new Order(1, new ArrayList<>(), new ArrayList<>(), "Pending", Date.valueOf("2023-10-01"), 50.0f);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(mockOrder));

        Order order = orderService.get(1);
        assertEquals("Pending", order.getStatus());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetOrderByIdNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.get(1));
    }

    @Test
    public void testAddOrder() {
        OrderRegistrationDto orderInDto = new OrderRegistrationDto(new ArrayList<>(), new ArrayList<>(), "Pending", Date.valueOf("2023-10-01"), 50.0f );
        Order mockOrder = new Order(1, new ArrayList<>(), new ArrayList<>(), "Pending", Date.valueOf("2023-10-01"), 50.0f);
        when(orderRepository.save(any(Order.class))).thenReturn(mockOrder);

        OrderOutDto orderOutDto = orderService.add(orderInDto);
        assertEquals("Pending", orderOutDto.getStatus());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    public void testModifyOrder() throws OrderNotFoundException {
        Order mockOrder = new Order(1, new ArrayList<>(), new ArrayList<>(), "Pending", Date.valueOf("2023-10-01"), 50.0f);
        OrderInDto orderInDto = new OrderInDto(new ArrayList<>(), new ArrayList<>(), "Pending", Date.valueOf("2023-10-01"), 50.0f);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(mockOrder));

        OrderOutDto updatedOrder = orderService.modify(1, orderInDto);
        assertEquals("Completed", updatedOrder.getStatus());
        verify(orderRepository, times(1)).save(mockOrder);
    }

    @Test
    public void testRemoveOrder() throws OrderNotFoundException {
        Order mockOrder = new Order(1, new ArrayList<>(), new ArrayList<>(), "Pending", Date.valueOf("2023-10-01"), 50.0f);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(mockOrder));

        orderService.remove(1);
        verify(orderRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testRemoveOrderNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.remove(1));
    }
}