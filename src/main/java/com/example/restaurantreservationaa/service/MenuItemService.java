package com.example.restaurantreservationaa.service;


import com.example.restaurantreservationaa.domain.MenuItem;
import com.example.restaurantreservationaa.domain.dto.menuitem.MenuItemInDto;
import com.example.restaurantreservationaa.domain.dto.menuitem.MenuItemOutDto;
import com.example.restaurantreservationaa.domain.dto.menuitem.MenuItemRegistrationDto;
import com.example.restaurantreservationaa.exception.MenuItemNotFoundException;
import com.example.restaurantreservationaa.repository.MenuItemRepository;
import jakarta.persistence.criteria.Predicate;
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

    public List<MenuItemOutDto> getAll(String name, String description) {
        List<MenuItem> menuItemList;

        if (name.isEmpty() && description.isEmpty()) {
            menuItemList = menuItemRepository.findAll();
        } else if (name.isEmpty()) {
            menuItemList = menuItemRepository.findByDescription(description);
        } else if (description.isEmpty()) {
            menuItemList = menuItemRepository.findByName(name);
        } else {
            menuItemList = menuItemRepository.findByNameAndDescription(name, description);
        }

        return modelMapper.map(menuItemList, new TypeToken<List<MenuItemOutDto>>() {
        }.getType());
    }

    public MenuItem get(long id) throws MenuItemNotFoundException {
        return menuItemRepository.findById(id).orElseThrow(MenuItemNotFoundException::new);
    }

    public List<MenuItem> getMenuItemsByFilter(String name, String description, String category) {
        return menuItemRepository.findAll((root, query, criteriaBuilder) -> {
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

    public MenuItemOutDto add(MenuItemRegistrationDto menuItemInDto) {
        MenuItem menuItem = modelMapper.map(menuItemInDto, MenuItem.class);
        MenuItem newMenuItem = menuItemRepository.save(menuItem);

        return modelMapper.map(newMenuItem, MenuItemOutDto.class);
    }

    public MenuItemOutDto modify(long menuItemId, MenuItemInDto menuItemInDto) throws MenuItemNotFoundException {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(MenuItemNotFoundException::new);

        modelMapper.map(menuItemInDto, menuItem);
        menuItemRepository.save(menuItem);

        return modelMapper.map(menuItem, MenuItemOutDto.class);
    }

    public void remove(long menuItemId) throws MenuItemNotFoundException {
        menuItemRepository.findById(menuItemId).orElseThrow(MenuItemNotFoundException::new);
        menuItemRepository.deleteById(menuItemId);
    }
}
