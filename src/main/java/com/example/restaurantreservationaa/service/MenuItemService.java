package com.example.restaurantreservationaa.service;

import com.example.restaurantreservationaa.domain.MenuItem;
import com.example.restaurantreservationaa.domain.Reservation;
import com.example.restaurantreservationaa.exception.MenuItemNotFoundException;
import com.example.restaurantreservationaa.exception.ReservationNotFoundException;
import com.example.restaurantreservationaa.repository.MenuItemRepository;
import com.example.restaurantreservationaa.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<MenuItem> getAll() {
        List<MenuItem> allMenuItems = menuItemRepository.findAll();
        return allMenuItems;
    }

    public MenuItem get(long id) throws MenuItemNotFoundException {
        return menuItemRepository.findById(id).orElseThrow(MenuItemNotFoundException::new);
    }

    public MenuItem add(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    public void remove(long menuItemId) throws MenuItemNotFoundException {
        menuItemRepository.findById(menuItemId).orElseThrow(MenuItemNotFoundException::new);
        menuItemRepository.deleteById(menuItemId);
    }
}
