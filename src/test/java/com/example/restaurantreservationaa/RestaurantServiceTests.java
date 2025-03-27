package com.example.restaurantreservationaa;

import com.example.restaurantreservationaa.domain.Restaurant;
import com.example.restaurantreservationaa.domain.dto.restaurant.RestaurantInDto;
import com.example.restaurantreservationaa.domain.dto.restaurant.RestaurantOutDto;
import com.example.restaurantreservationaa.domain.dto.restaurant.RestaurantRegistrationDto;
import com.example.restaurantreservationaa.exception.RestaurantNotFoundException;
import com.example.restaurantreservationaa.repository.RestaurantRepository;
import com.example.restaurantreservationaa.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTests {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Test
    public void testGetAll() {
        List<Restaurant> mockRestaurantList = List.of(
                new Restaurant(1, "Restaurant A", "Address A", "1234567890", 0.0, 0.0),
                new Restaurant(2, "Restaurant B", "Address B", "0987654321", 0.0, 0.0),
                new Restaurant(3, "Restaurant C", "Address C", "1122334455", 0.0, 0.0)
        );

        when(restaurantRepository.findAll()).thenReturn(mockRestaurantList);

        List<RestaurantOutDto> restaurantList = restaurantService.getAll("", "");
        assertEquals(3, restaurantList.size());
        assertEquals("Restaurant A", restaurantList.get(0).getName());
        assertEquals("Restaurant C", restaurantList.get(2).getName());
        verify(restaurantRepository, times(1)).findAll();
    }

    @Test
    public void testGetRestaurantById() throws RestaurantNotFoundException {
        Restaurant mockRestaurant = new Restaurant(1, "Restaurant A", "Address A", "1234567890", 0.0, 0.0);
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(mockRestaurant));

        Restaurant restaurant = restaurantService.get(1);
        assertEquals("Restaurant A", restaurant.getName());
        verify(restaurantRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetRestaurantByIdNotFound() {
        when(restaurantRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.get(1));
    }

    @Test
    public void testAddRestaurant() {
        RestaurantRegistrationDto restaurantInDto = new RestaurantRegistrationDto(1, "Restaurant A", "Address A", "1234567890", 0.0, 0.0);
        Restaurant mockRestaurant = new Restaurant(1, "Restaurant A", "Address A", "1234567890", 0.0, 0.0);
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(mockRestaurant);

        RestaurantOutDto restaurantOutDto = restaurantService.add(restaurantInDto);
        assertEquals("Restaurant A", restaurantOutDto.getName());
        verify(restaurantRepository, times(1)).save(any(Restaurant.class));
    }

    @Test
    public void testModifyRestaurant() throws RestaurantNotFoundException {
        Restaurant mockRestaurant = new Restaurant(1, "Restaurant A", "Address A", "1234567890", 0.0, 0.0);
        RestaurantInDto restaurantInDto = new RestaurantInDto("Restaurant B", "Address B", "0987654321");
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(mockRestaurant));

        RestaurantOutDto updatedRestaurant = restaurantService.modify(1, restaurantInDto);
        assertEquals("Restaurant B", updatedRestaurant.getName());
        verify(restaurantRepository, times(1)).save(mockRestaurant);
    }

    @Test
    public void testRemoveRestaurant() throws RestaurantNotFoundException {
        Restaurant mockRestaurant = new Restaurant(1, "Restaurant A", "Address A", "1234567890", 0.0, 0.0);
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(mockRestaurant));

        restaurantService.remove(1);
        verify(restaurantRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testRemoveRestaurantNotFound() {
        when(restaurantRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.remove(1));
    }
}