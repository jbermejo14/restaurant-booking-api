package com.example.restaurantreservationaa;

import com.example.restaurantreservationaa.domain.MenuItem;
import com.example.restaurantreservationaa.domain.Order;
import com.example.restaurantreservationaa.domain.dto.menuitem.MenuItemInDto;
import com.example.restaurantreservationaa.domain.dto.menuitem.MenuItemOutDto;
import com.example.restaurantreservationaa.domain.dto.menuitem.MenuItemRegistrationDto;
import com.example.restaurantreservationaa.exception.MenuItemNotFoundException;
import com.example.restaurantreservationaa.repository.MenuItemRepository;
import com.example.restaurantreservationaa.service.MenuItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MenuItemServiceTests {

    @InjectMocks
    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private ModelMapper modelMapper;

//    @Test
//    public void testGetAll() {
//        List<MenuItem> mockMenuItemList = List.of(
//                new MenuItem(1, "Pizza", "Cheese Pizza", 8.5f, "Main Course", false, new ArrayList<>()),
//                new MenuItem(2, "Salad", "Caesar Salad", 5.0f, "Appetizer", true, new ArrayList<>()),
//                new MenuItem(3, "Ice Cream", "Vanilla Ice Cream", 3.0f, "Dessert", true, new ArrayList<>())
//        );
//
//        when(menuItemRepository.findAll()).thenReturn(mockMenuItemList);
//
//        List<MenuItemOutDto> menuItemList = menuItemService.getAll("", "");
//        assertEquals(3, menuItemList.size());
//        assertEquals("Pizza", menuItemList.get(0).getName());
//        assertEquals("Ice Cream", menuItemList.get(2).getName());
//        verify(menuItemRepository, times(1)).findAll();
//    }

//    @Test
//    public void testGetMenuItemById() throws MenuItemNotFoundException {
//        MenuItem mockMenuItem = new MenuItem(1, "Pizza", "Cheese Pizza", 8.5f, "Main Course", false, new ArrayList<>());
//        when(menuItemRepository.findById(1L)).thenReturn(Optional.of(mockMenuItem));
//
//        MenuItem menuItem = menuItemService.get(1);
//        assertEquals("Pizza", menuItem.getName());
//        verify(menuItemRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    public void testGetMenuItemByIdNotFound() {
//        when(menuItemRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(MenuItemNotFoundException.class, () -> menuItemService.get(1));
//    }
//
//    @Test
//    public void testAddMenuItem() {
//        MenuItemRegistrationDto menuItemInDto = new MenuItemRegistrationDto("Pizza", "Cheese Pizza", 8.5f, "Main Course", false);
//        MenuItem mockMenuItem = new MenuItem(1, "Pizza", "Cheese Pizza", 8.5f, "Main Course", false, new ArrayList<>());
//        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(mockMenuItem);
//
//        MenuItemOutDto menuItemOutDto = menuItemService.add(menuItemInDto);
//        assertEquals("Pizza", menuItemOutDto.getName());
//        verify(menuItemRepository, times(1)).save(any(MenuItem.class));
//    }
//
//    @Test
//    public void testModifyMenuItem() throws MenuItemNotFoundException {
//        MenuItem mockMenuItem = new MenuItem(1, "Pizza", "Cheese Pizza", 8.5f, "Main Course", false, new ArrayList<>());
//        MenuItemInDto menuItemInDto = new MenuItemInDto("Pasta", "Creamy Pasta", 9.0f, "Main Course", false);
//        when(menuItemRepository.findById(1L)).thenReturn(Optional.of(mockMenuItem));
//
//        MenuItemOutDto updatedMenuItem = menuItemService.modify(1, menuItemInDto);
//        assertEquals("Pasta", updatedMenuItem.getName());
//        verify(menuItemRepository, times(1)).save(mockMenuItem);
//    }
//
//    @Test
//    public void testRemoveMenuItem() throws MenuItemNotFoundException {
//        MenuItem mockMenuItem = new MenuItem(1, "Pizza", "Cheese Pizza", 8.5f, "Main Course", false, new ArrayList<>());
//        when(menuItemRepository.findById(1L)).thenReturn(Optional.of(mockMenuItem));
//
//        menuItemService.remove(1);
//        verify(menuItemRepository, times(1)).deleteById(1L);
//    }
//
//    @Test
//    public void testRemoveMenuItemNotFound() {
//        when(menuItemRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(MenuItemNotFoundException.class, () -> menuItemService.remove(1));
//    }
}