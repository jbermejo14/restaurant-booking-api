package com.example.restaurantreservationaa.service;

import com.example.restaurantreservationaa.domain.Beverage;
import com.example.restaurantreservationaa.exception.BeverageNotFoundException;
import com.example.restaurantreservationaa.repository.BeverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeverageService {

    @Autowired
    private BeverageRepository beverageRepository;

    public List<Beverage> getAll() {
        List<Beverage> allBeverages = beverageRepository.findAll();
        return allBeverages;
    }

    public Beverage get(long id) throws BeverageNotFoundException {
        return beverageRepository.findById(id).orElseThrow(BeverageNotFoundException::new);
    }

    public Beverage add(Beverage beverage) {
        return beverageRepository.save(beverage);
    }

    public void remove(long beverageId) throws BeverageNotFoundException {
        beverageRepository.findById(beverageId).orElseThrow(BeverageNotFoundException::new);
        beverageRepository.deleteById(beverageId);
    }
}
