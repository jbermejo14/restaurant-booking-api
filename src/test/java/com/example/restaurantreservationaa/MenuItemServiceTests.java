package com.example.restaurantreservationaa;

import com.example.restaurantreservationaa.domain.MenuItem;
import com.example.restaurantreservationaa.domain.dto.menuitem.MenuItemOutDto;
import com.example.restaurantreservationaa.domain.dto.menuitem.MenuItemRegistrationDto;
import com.example.restaurantreservationaa.exception.MenuItemNotFoundException;
import com.example.restaurantreservationaa.repository.MenuItemRepository;
import com.example.restaurantreservationaa.service.MenuItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.sql.Date;
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

    @Test
    public void testGetAll() {
        List<MenuItem> mockMenuItemList = List.of(
                new MenuItem(1, "Pizza", "Cheese Pizza", 8.5f, "Main Course", false, new ArrayList<>()),
                new MenuItem(2, "Salad", "Caesar Salad", 5.0f, "Appetizer", true, new ArrayList<>()),
                new MenuItem(3, "Ice Cream", "Vanilla Ice Cream", 3.0f, "Dessert", true, new ArrayList<>())
        );

        List<MenuItemOutDto> mockMenuItemOutDtoList = List.of(
                new MenuItemOutDto(31, "Pizza", "Cheese Pizza", 8.5f, "Main Course", false),
                new MenuItemOutDto(32, "Salad", "Caesar Salad", 5.0f, "Appetizer", true),
                new MenuItemOutDto(33, "Ice Cream", "Vanilla Ice Cream", 3.0f, "Dessert", true)
        );

        when(menuItemRepository.findAll()).thenReturn(mockMenuItemList);
        when(modelMapper.map(mockMenuItemList, new TypeToken<List<MenuItemOutDto>>() {}.getType())).thenReturn(mockMenuItemOutDtoList);

        List<MenuItemOutDto> menuList = menuItemService.getAll("", "");
        assertEquals(3, menuList.size());
        assertEquals("Pizza", menuList.get(0).getName());
        assertEquals(8.5f, menuList.get(0).getPrice());
        verify(menuItemRepository, times(1)).findAll();
    }


    @Test
    public void testGetMenuItemById() throws MenuItemNotFoundException {
        MenuItem mockMenuItem = new MenuItem(1, "Pizza", "Cheese Pizza", 8.5f, "Main Course", false, new ArrayList<>());
        when(menuItemRepository.findById(1L)).thenReturn(Optional.of(mockMenuItem));

        MenuItem menuItem = menuItemService.get(1);
        assertEquals("Pizza", menuItem.getName());
        verify(menuItemRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetMenuItemByIdNotFound() {
        when(menuItemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(MenuItemNotFoundException.class, () -> menuItemService.get(1));
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    public void testAdd() {
        MenuItemRegistrationDto menuItemRegistrationDto = new MenuItemRegistrationDto("Ice Cream", "Vanilla Ice Cream", 3.0f, "Dessert", true);

        MenuItem mockMenuItem = new MenuItem(0, "Ice Cream", "Vanilla Ice Cream", 3.0f, "Dessert", true, new ArrayList<>());
        MenuItemOutDto mockMenuItemOutDto = new MenuItemOutDto(31, "Ice Cream", "Vanilla Ice Cream", 3.0f, "Dessert", true);

        when(modelMapper.map(menuItemRegistrationDto, MenuItem.class)).thenReturn(mockMenuItem);
        when(modelMapper.map(mockMenuItem, MenuItemOutDto.class)).thenReturn(mockMenuItemOutDto);

        menuItemService.add(menuItemRegistrationDto);

        assertEquals(31, mockMenuItemOutDto.getId());
        assertEquals("Ice Cream", mockMenuItemOutDto.getName());
        assertEquals(3.0f, mockMenuItemOutDto.getPrice());

        verify(menuItemRepository, times(1)).save(mockMenuItem);
    }


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

    @Test
    public void testRemoveMenuItem() throws MenuItemNotFoundException {
        MenuItem mockMenuItem = new MenuItem(1, "Pizza", "Cheese Pizza", 8.5f, "Main Course", false, new ArrayList<>());
        when(menuItemRepository.findById(1L)).thenReturn(Optional.of(mockMenuItem));

        menuItemService.remove(1);
        verify(menuItemRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testRemoveMenuItemNotFound() {
        when(menuItemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(MenuItemNotFoundException.class, () -> menuItemService.remove(1));
    }
}