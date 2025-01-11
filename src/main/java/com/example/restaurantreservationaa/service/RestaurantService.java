package com.example.restaurantreservationaa.service;

import com.example.restaurantreservationaa.domain.Restaurant;
import com.example.restaurantreservationaa.domain.dto.restaurant.RestaurantInDto;
import com.example.restaurantreservationaa.domain.dto.restaurant.RestaurantOutDto;
import com.example.restaurantreservationaa.domain.dto.restaurant.RestaurantRegistrationDto;
import com.example.restaurantreservationaa.exception.RestaurantNotFoundException;
import com.example.restaurantreservationaa.repository.RestaurantRepository;
import jakarta.persistence.criteria.Predicate;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.Date;
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

    public List<Restaurant> getRestaurantsByFilter(String name, String address, String phone) {
        return restaurantRepository.findAll((root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            // Check for orderDate filter
            if (name != null && !name.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("name"), name));
            }
            // Check for totalPrice filter
            if (address != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("address"), address));
            }
            // Check for status filter
            if (phone != null && !phone.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("phone"), "%" + phone + "%"));
            }

            return predicate;
        });
    }

    public RestaurantOutDto add(RestaurantRegistrationDto restaurantInDto) {
        Restaurant restaurant = modelMapper.map(restaurantInDto, Restaurant.class);
        Restaurant newRestaurant = restaurantRepository.save(restaurant);

        return modelMapper.map(newRestaurant, RestaurantOutDto.class);
    }

    public RestaurantOutDto modify(long restaurantId, RestaurantInDto restaurantInDto) throws RestaurantNotFoundException {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(RestaurantNotFoundException::new);

        modelMapper.map(restaurantInDto, restaurant);
        restaurantRepository.save(restaurant);

        return modelMapper.map(restaurant, RestaurantOutDto.class);
    }

    public void remove(long restaurantId) throws RestaurantNotFoundException {
        restaurantRepository.findById(restaurantId).orElseThrow(RestaurantNotFoundException::new);
        restaurantRepository.deleteById(restaurantId);
    }
}
