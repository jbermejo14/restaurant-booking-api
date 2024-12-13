package com.example.restaurantreservationaa.service;

import com.example.restaurantreservationaa.domain.MenuItem;
import com.example.restaurantreservationaa.domain.Restaurant;
import com.example.restaurantreservationaa.domain.dto.menuitem.MenuItemOutDto;
import com.example.restaurantreservationaa.domain.dto.menuitem.MenuItemRegistrationDto;
import com.example.restaurantreservationaa.domain.dto.restaurant.RestaurantOutDto;
import com.example.restaurantreservationaa.domain.dto.restaurant.RestaurantRegistrationDto;
import com.example.restaurantreservationaa.exception.MenuItemNotFoundException;
import com.example.restaurantreservationaa.repository.MenuItemRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<MenuItemOutDto> getAll(String name, String description, double price) {
        List<MenuItem> menuItemList;

        if (name.isEmpty() && description.isEmpty()) {
            menuItemList = menuItemRepository.findAll();
        } else if (name.isEmpty()) {
            menuItemList = menuItemRepository.findByPrice(price);
        } else if (description.isEmpty()) {
            menuItemList = menuItemRepository.findByName(name);
        } else {
            menuItemList = menuItemRepository.findByNameAndPrice(name, price);
        }

        return modelMapper.map(menuItemList, new TypeToken<List<MenuItemOutDto>>() {
        }.getType());
    }

    public MenuItem get(long id) throws MenuItemNotFoundException {
        return menuItemRepository.findById(id).orElseThrow(MenuItemNotFoundException::new);
    }

    public MenuItemOutDto add(MenuItemRegistrationDto menuItemInDto) {
        MenuItem menuItem = modelMapper.map(menuItemInDto, MenuItem.class);
        MenuItem newMenuItem = menuItemRepository.save(menuItem);

        return modelMapper.map(newMenuItem, MenuItemOutDto.class);
    }

    public void remove(long menuItemId) throws MenuItemNotFoundException {
        menuItemRepository.findById(menuItemId).orElseThrow(MenuItemNotFoundException::new);
        menuItemRepository.deleteById(menuItemId);
    }
}
