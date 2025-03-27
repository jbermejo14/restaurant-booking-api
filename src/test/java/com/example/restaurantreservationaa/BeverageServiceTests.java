package com.example.restaurantreservationaa;

import com.example.restaurantreservationaa.domain.Beverage;
import com.example.restaurantreservationaa.domain.Order;
import com.example.restaurantreservationaa.domain.dto.beverage.BeverageInDto;
import com.example.restaurantreservationaa.domain.dto.beverage.BeverageOutDto;
import com.example.restaurantreservationaa.domain.dto.beverage.BeverageRegistrationDto;
import com.example.restaurantreservationaa.exception.BeverageNotFoundException;
import com.example.restaurantreservationaa.repository.BeverageRepository;
import com.example.restaurantreservationaa.service.BeverageService;

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
public class BeverageServiceTests {

    @InjectMocks
    private BeverageService beverageService;

    @Mock
    private BeverageRepository beverageRepository;

    @Mock
    private ModelMapper modelMapper;
//
//    @Test
//    public void testGetAll() {
//        List<Beverage> mockBeverageList = List.of(
//                new Beverage(1, "Coca Cola", "Soda", 1.5f, "Beverage", new ArrayList<>()),
//                new Beverage(2, "Pepsi", "Soda", 1.5f, "Beverage", new ArrayList<>()),
//                new Beverage(3, "Orange Juice", "Juice", 2.0f, "Beverage", new ArrayList<>())
//        );
//
//        when(beverageRepository.findAll()).thenReturn(mockBeverageList);
//
//        List<BeverageOutDto> beverageList = beverageService.getAll("", "");
//        assertEquals(3, beverageList.size());
//        assertEquals("Coca Cola", beverageList.get(0).getName());
//        assertEquals("Orange Juice", beverageList.get(2).getName());
//        verify(beverageRepository, times(1)).findAll();
//    }
////
//    @Test
//    public void testGetBeverageById() throws BeverageNotFoundException {
//        Beverage mockBeverage = new Beverage(1, "Coca Cola", "Soda", 1.5f, "Beverage", new ArrayList<>());
//        when(beverageRepository.findById(1L)).thenReturn(Optional.of(mockBeverage));
//
//        Beverage beverage = beverageService.get(1);
//        assertEquals("Coca Cola", beverage.getName());
//        verify(beverageRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    public void testGetBeverageByIdNotFound() {
//        when(beverageRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(BeverageNotFoundException.class, () -> beverageService.get(1));
//    }
//
//    @Test
//    public void testAddBeverage() {
//        BeverageRegistrationDto beverageInDto = new BeverageRegistrationDto("Coca Cola", "Soda", 1.5f, "Beverage");
//        Beverage mockBeverage = new Beverage(1, "Coca Cola", "Soda", 1.5f, "Beverage", new ArrayList<>());
//        when(beverageRepository.save(any(Beverage.class))).thenReturn(mockBeverage);
//
//        BeverageOutDto beverageOutDto = beverageService.add(beverageInDto);
//        assertEquals("Coca Cola", beverageOutDto.getName());
//        verify(beverageRepository, times(1)).save(any(Beverage.class));
//    }
//
//    @Test
//    public void testModifyBeverage() throws BeverageNotFoundException {
//        Beverage mockBeverage = new Beverage(1, "Coca Cola", "Soda", 1.5f, "Beverage", new ArrayList<>());
//        BeverageInDto beverageInDto = new BeverageInDto("Pepsi", "Soda", 1.5f, "Beverage");
//        when(beverageRepository.findById(1L)).thenReturn(Optional.of(mockBeverage));
//
//        BeverageOutDto updatedBeverage = beverageService.modify(1, beverageInDto);
//        assertEquals("Pepsi", updatedBeverage.getName());
//        verify(beverageRepository, times(1)).save(mockBeverage);
//    }
//
//    @Test
//    public void testRemoveBeverage() throws BeverageNotFoundException {
//        Beverage mockBeverage = new Beverage(1, "Coca Cola", "Soda", 1.5f, "Beverage", new ArrayList<>());
//        when(beverageRepository.findById(1L)).thenReturn(Optional.of(mockBeverage));
//
//        beverageService.remove(1);
//        verify(beverageRepository, times(1)).deleteById(1L);
//    }
//
//    @Test
//    public void testRemoveBeverageNotFound() {
//        when(beverageRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(BeverageNotFoundException.class, () -> beverageService.remove(1));
//    }
}