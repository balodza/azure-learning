package com.bob.azure.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bob.azure.entity.mssql.Car;
import com.bob.azure.entity.mssql.Make;
import com.bob.azure.repository.mssql.CarRepository;
import com.bob.azure.repository.mssql.MakeRepository;

@ExtendWith(MockitoExtension.class)
class MakeServiceImplTest {

    @Mock
    MakeRepository makeRepository;
    @Mock
    CarRepository carRepository;
    @InjectMocks
    MakeServiceImpl makeService;

    @Test
    void getGroups_returnsAllMakes() {
        var make = Make.builder().id(1).name("Ford").build();
        when(makeRepository.findAll()).thenReturn(List.of(make));

        var result = makeService.getGroups();

        assertThat(result).hasSize(1).contains(make);
    }

    @Test
    void create_savesAndReturnsMake() {
        var saved = Make.builder().id(1).name("Toyota").build();
        when(makeRepository.save(any(Make.class))).thenReturn(saved);

        var result = makeService.create("Toyota");

        assertThat(result.getName()).isEqualTo("Toyota");
        verify(makeRepository).save(any(Make.class));
    }

    @Test
    void delete_deletesAllCarsForMakeBeforeDeletingMake() {
        var car = Car.builder().id(1).model("Mustang").build();
        when(carRepository.findCarsByMakeId(1)).thenReturn(List.of(car));

        makeService.delete(1);

        var inOrder = inOrder(carRepository, makeRepository);
        inOrder.verify(carRepository).deleteAll(List.of(car));
        inOrder.verify(makeRepository).deleteById(1);
    }
}
