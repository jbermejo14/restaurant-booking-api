package com.example.restaurantreservationaa.service;

import com.example.restaurantreservationaa.domain.Beverage;
import com.example.restaurantreservationaa.domain.Customer;
import com.example.restaurantreservationaa.domain.MenuItem;
import com.example.restaurantreservationaa.domain.Restaurant;
import com.example.restaurantreservationaa.domain.dto.beverage.BeverageOutDto;
import com.example.restaurantreservationaa.domain.dto.beverage.BeverageRegistrationDto;
import com.example.restaurantreservationaa.domain.dto.menuitem.MenuItemOutDto;
import com.example.restaurantreservationaa.domain.dto.menuitem.MenuItemRegistrationDto;
import com.example.restaurantreservationaa.domain.dto.restaurant.RestaurantOutDto;
import com.example.restaurantreservationaa.exception.BeverageNotFoundException;
import com.example.restaurantreservationaa.repository.BeverageRepository;
import jakarta.persistence.criteria.Predicate;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeverageService {

    @Autowired
    private BeverageRepository beverageRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<BeverageOutDto> getAll(String name, String description) {
        List<Beverage> beverageList;

        if (name.isEmpty() && description.isEmpty()) {
            beverageList = beverageRepository.findAll();
        } else if (name.isEmpty()) {
            beverageList = beverageRepository.findByName(name);
        } else if (description.isEmpty()) {
            beverageList = beverageRepository.findByDescription(description);
        } else {
            beverageList = beverageRepository.findByNameAndDescription(name, description);
        }

        return modelMapper.map(beverageList, new TypeToken<List<BeverageOutDto>>() {
        }.getType());
    }

    public Beverage get(long id) throws BeverageNotFoundException {
        return beverageRepository.findById(id).orElseThrow(BeverageNotFoundException::new);
    }

    public List<Beverage> getBeveragesByFilter(String name, String description, String category) {
        return beverageRepository.findAll((root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (name != null && !name.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            if (description != null && !description.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("description"), "%" + description + "%"));
            }
            if (category != null && !category.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("category"), "%" + category + "%"));
            }

            return predicate;
        });
    }

    public BeverageOutDto add(BeverageRegistrationDto beverageInDto) {
        Beverage beverage = modelMapper.map(beverageInDto, Beverage.class);
        Beverage newBeverage = beverageRepository.save(beverage);

        return modelMapper.map(newBeverage, BeverageOutDto.class);
    }

    public void remove(long beverageId) throws BeverageNotFoundException {
        beverageRepository.findById(beverageId).orElseThrow(BeverageNotFoundException::new);
        beverageRepository.deleteById(beverageId);
    }
}
