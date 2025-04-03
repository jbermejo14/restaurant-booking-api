package com.example.restaurantreservationaa;

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
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

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

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testGetAll() {
        List<Order> mockOrderList = List.of(
                new Order(1, new ArrayList<>(), new ArrayList<>(), "Pending", Date.valueOf("2023-10-01"), 50.0f),
                new Order(2, new ArrayList<>(), new ArrayList<>(), "Completed", Date.valueOf("2023-10-02"), 75.0f),
                new Order(3, new ArrayList<>(), new ArrayList<>(), "Pending", Date.valueOf("2023-10-03"), 100.0f)
        );

        List<OrderOutDto> mockOrderOutDtoList = List.of(
                new OrderOutDto(31, new ArrayList<>(), new ArrayList<>(), "Pending", Date.valueOf("2023-10-01"), 50.0f),
                new OrderOutDto(32, new ArrayList<>(), new ArrayList<>(), "Completed", Date.valueOf("2023-10-01"), 75.0f),
                new OrderOutDto(33, new ArrayList<>(), new ArrayList<>(), "Pending", Date.valueOf("2023-10-01"), 50.0f)
        );

        when(orderRepository.findAll()).thenReturn(mockOrderList);
        when(modelMapper.map(mockOrderList, new TypeToken<List<OrderOutDto>>() {}.getType())).thenReturn(mockOrderOutDtoList);

        List<OrderOutDto> orderList = orderService.getAll(0.0f, null);
        assertEquals(3, orderList.size());
        assertEquals("Pending", orderList.get(0).getStatus());
        assertEquals(50.0f, orderList.get(0).getTotalPrice());
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
    @MockitoSettings(strictness = Strictness.LENIENT)
    public void testAdd() {
        OrderRegistrationDto orderRegistrationDto = new OrderRegistrationDto(new ArrayList<>(), new ArrayList<>(), "Pending", Date.valueOf("2023-10-01"), 50.0f);

        Order mockOrder = new Order(0, new ArrayList<>(), new ArrayList<>(), "Pending", Date.valueOf("2023-10-01"), 50.0f);
        OrderOutDto mockOrderOutDto = new OrderOutDto(31, new ArrayList<>(), new ArrayList<>(), "Pending", Date.valueOf("2023-10-01"), 50.0f);

        when(modelMapper.map(orderRegistrationDto, Order.class)).thenReturn(mockOrder);
        when(modelMapper.map(mockOrder, OrderOutDto.class)).thenReturn(mockOrderOutDto);

        orderService.add(orderRegistrationDto);

        assertEquals(31, mockOrderOutDto.getId());
        assertEquals("Pending", mockOrderOutDto.getStatus());
        assertEquals(50.0f, mockOrderOutDto.getTotalPrice());

        verify(orderRepository, times(1)).save(mockOrder);
    }

//    @Test
//    public void testModifyOrder() throws OrderNotFoundException {
//        Order mockOrder = new Order(1, new ArrayList<>(), new ArrayList<>(), "Pending", Date.valueOf("2023-10-01"), 50.0f);
//        OrderInDto orderInDto = new OrderInDto(new ArrayList<>(), new ArrayList<>(), "Completed", Date.valueOf("2023-10-01"), 50.0f);
//        when(orderRepository.findById(1L)).thenReturn(Optional.of(mockOrder));
//        when(modelMapper.map(orderInDto, Order.class)).thenReturn(mockOrder);
//        when(orderRepository.save(mockOrder)).thenReturn(mockOrder);
//        when(modelMapper.map(mockOrder, OrderOutDto.class)).thenReturn(new OrderOutDto(1, new ArrayList<>(), new ArrayList<>(), "Completed", Date.valueOf("2023-10-01"), 50.0f));
//
//        OrderOutDto updatedOrder = orderService.modify(1, orderInDto);
//        assertEquals("Completed", updatedOrder.getStatus());
//        verify(orderRepository, times(1)).save(mockOrder);
//    }

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