package com.example.restaurantreservationaa.service;

import com.example.restaurantreservationaa.domain.Beverage;
import com.example.restaurantreservationaa.domain.dto.beverage.BeverageInDto;
import com.example.restaurantreservationaa.domain.dto.beverage.BeverageOutDto;
import com.example.restaurantreservationaa.domain.dto.beverage.BeverageRegistrationDto;
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

    public BeverageOutDto modify(long beverageId, BeverageInDto beverageInDto) throws BeverageNotFoundException {
        Beverage beverage = beverageRepository.findById(beverageId)
                .orElseThrow(BeverageNotFoundException::new);

        modelMapper.map(beverageInDto, beverage);
        beverageRepository.save(beverage);

        return modelMapper.map(beverage, BeverageOutDto.class);
    }

    public BeverageOutDto partialUpdate(long beverageId, BeverageInDto beverageInDto) throws BeverageNotFoundException {
        // Retrieve the existing beverage
        Beverage beverage = get(beverageId);

        // Update only the fields that are present in the request
        if (beverageInDto.getName() != null) {
            beverage.setName(beverageInDto.getName());
        }
        if (beverageInDto.getDescription() != null) {
            beverage.setDescription(beverageInDto.getDescription());
        }
        if (beverageInDto.getPrice() != null) {
            beverage.setPrice(beverageInDto.getPrice());
        }
        if (beverageInDto.getCategory() != null) {
            beverage.setCategory(beverageInDto.getCategory());
        }

        // Save the updated beverage
        beverageRepository.save(beverage);

        // Return the updated DTO
        return modelMapper.map(beverage, BeverageOutDto.class);
    }


    public void remove(long beverageId) throws BeverageNotFoundException {
        beverageRepository.findById(beverageId).orElseThrow(BeverageNotFoundException::new);
        beverageRepository.deleteById(beverageId);
    }
}
