package com.bob.azure.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bob.azure.dto.CreateCarDto;
import com.bob.azure.entity.cosmos.CosmosHistory;
import com.bob.azure.entity.mongo.History;
import com.bob.azure.entity.mssql.Car;
import com.bob.azure.entity.mssql.Make;
import com.bob.azure.repository.cosmos.CosmosHistoryRepository;
import com.bob.azure.repository.mongo.MongoHistoryRepository;
import com.bob.azure.repository.mssql.CarRepository;
import com.bob.azure.repository.mssql.MakeRepository;
import com.bob.azure.service.FileService;
import com.bob.azure.service.QueueService;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    CarRepository carRepository;
    @Mock
    MakeRepository makeRepository;
    @Mock
    MongoHistoryRepository mongoHistoryRepository;
    @Mock
    CosmosHistoryRepository cosmosHistoryRepository;
    @Mock
    FileService fileService;
    @Mock
    QueueService queueService;
    @Mock
    JsonService jsonService;
    @InjectMocks
    CarServiceImpl carService;

    @Test
    void getCars_returnsAllCarsAndSavesHistory() {
        var make = Make.builder().id(1).name("Ford").build();
        var car = Car.builder().id(1).model("Mustang").make(make).build();
        when(carRepository.findAll()).thenReturn(List.of(car));
        when(jsonService.toString(any())).thenReturn("[{\"id\":1}]");

        var result = carService.getCars();

        assertThat(result).hasSize(1).contains(car);
        verify(fileService).uploadFile(anyString(), eq("[{\"id\":1}]"));
        verify(mongoHistoryRepository).save(any(History.class));
        verify(cosmosHistoryRepository).save(any(CosmosHistory.class));
    }

    @Test
    void getById_returnsCarAndSavesHistory() {
        var car = Car.builder().id(1).model("Mustang").build();
        when(carRepository.getCarById(1)).thenReturn(car);
        when(jsonService.toString(car)).thenReturn("{\"id\":1}");

        var result = carService.getById(1);

        assertThat(result).isEqualTo(car);
        verify(fileService).uploadFile(anyString(), eq("{\"id\":1}"));
        verify(mongoHistoryRepository).save(any(History.class));
        verify(cosmosHistoryRepository).save(any(CosmosHistory.class));
    }

    @Test
    void search_returnsMatchingCarsAndSavesHistory() {
        var car = Car.builder().id(1).model("Mustang").build();
        when(carRepository.getCarsByModelContains("Mus")).thenReturn(List.of(car));
        when(jsonService.toString(any())).thenReturn("[{\"id\":1}]");

        var result = carService.search("Mus");

        assertThat(result).hasSize(1).contains(car);
        verify(fileService).uploadFile(anyString(), eq("[{\"id\":1}]"));
        verify(mongoHistoryRepository).save(any(History.class));
        verify(cosmosHistoryRepository).save(any(CosmosHistory.class));
    }

    @Test
    void create_whenMakeFound_savesCarAndSavesHistory() {
        var make = Make.builder().id(1).name("Ford").build();
        var dto = new CreateCarDto();
        dto.setMake("Ford");
        dto.setModel("Mustang");
        dto.setYear(2024);
        dto.setVersion("GT");
        dto.setEnginePistons(8);
        dto.setEngineVolume(5.0);
        dto.setEnginePower(450);

        var savedCar = Car.builder().id(1).model("Mustang").make(make).build();
        when(makeRepository.findByName("Ford")).thenReturn(make);
        when(carRepository.save(any(Car.class))).thenReturn(savedCar);
        when(jsonService.toString(savedCar)).thenReturn("{\"id\":1}");

        var result = carService.create(dto);

        assertThat(result).isEqualTo(savedCar);
        verify(carRepository).save(any(Car.class));
        verify(fileService).uploadFile(anyString(), eq("{\"id\":1}"));
        verify(mongoHistoryRepository).save(any(History.class));
        verify(cosmosHistoryRepository).save(any(CosmosHistory.class));
    }

    @Test
    void create_whenMakeNotFound_throwsRuntimeException() {
        var dto = new CreateCarDto();
        dto.setMake("Unknown");
        when(makeRepository.findByName("Unknown")).thenReturn(null);

        assertThatThrownBy(() -> carService.create(dto))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Make not found: Unknown");

        verify(carRepository, never()).save(any());
    }
}
