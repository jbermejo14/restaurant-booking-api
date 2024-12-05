package com.example.restaurantreservationaa.service;

import com.example.restaurantreservationaa.domain.Restaurant;
import com.example.restaurantreservationaa.domain.dto.restaurant.RestaurantOutDto;
import com.example.restaurantreservationaa.domain.dto.restaurant.RestaurantRegistrationDto;
import com.example.restaurantreservationaa.exception.RestaurantNotFoundException;
import com.example.restaurantreservationaa.repository.RestaurantRepository;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<RestaurantOutDto> getAll(String name, String address) {
        List<Restaurant> restaurantList;

        if (name.isEmpty() && address.isEmpty()) {
            restaurantList = restaurantRepository.findAll();
        } else if (name.isEmpty()) {
            restaurantList = restaurantRepository.findByAddress(address);
        } else if (address.isEmpty()) {
            restaurantList = restaurantRepository.findByName(name);
        } else {
            restaurantList = restaurantRepository.findByNameAndAddress(name, address);
        }

        return modelMapper.map(restaurantList, new TypeToken<List<RestaurantOutDto>>() {
        }.getType());
    }

    public Restaurant get(long id) throws RestaurantNotFoundException {
        return restaurantRepository.findById(id).orElseThrow(RestaurantNotFoundException::new);
    }

    public RestaurantOutDto add(RestaurantRegistrationDto restaurantInDto) {
        Restaurant restaurant = modelMapper.map(restaurantInDto, Restaurant.class);
        Restaurant newRestaurant = restaurantRepository.save(restaurant);

        return modelMapper.map(newRestaurant, RestaurantOutDto.class);
    }

    public void remove(long restaurantId) throws RestaurantNotFoundException {
        restaurantRepository.findById(restaurantId).orElseThrow(RestaurantNotFoundException::new);
        restaurantRepository.deleteById(restaurantId);
    }
}
