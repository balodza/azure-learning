package com.bob.azure.service.impl;


import java.util.List;
import java.util.UUID;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import com.bob.azure.dto.CreateCarDto;
import com.bob.azure.dto.DeleteCarDto;
import com.bob.azure.entity.cosmos.CosmosHistory;
import com.bob.azure.entity.mongo.History;
import com.bob.azure.entity.mssql.Car;
import com.bob.azure.entity.mssql.Make;
import com.bob.azure.repository.cosmos.CosmosHistoryRepository;
import com.bob.azure.repository.mongo.MongoHistoryRepository;
import com.bob.azure.repository.mssql.CarRepository;
import com.bob.azure.repository.mssql.MakeRepository;
import com.bob.azure.service.CarService;
import com.bob.azure.service.FileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final MakeRepository makeRepository;

    private final MongoHistoryRepository mongoHistoryRepository;

    private final CosmosHistoryRepository cosmosHistoryRepository;

    private final FileService fileService;

    private final JsonService jsonService;

    @Override
    public List<Car> getCars() {
        final var cars = carRepository.findAll();
        final var payload = jsonService.toString(cars);
        fileService.uploadFile(getFileName("getCars"), payload);
        saveHistory(payload);
        return IterableUtils.toList(cars);
    }

    @Override
    public Car getById(int id) {
        final var carById = carRepository.getCarById(id);
        final var payload = jsonService.toString(carById);
        fileService.uploadFile(getFileName("getById_%s".formatted(id)), payload);
        saveHistory(payload);
        return carById;
    }

    @Override
    public List<Car> search(String name) {
        final var result = carRepository.getCarsByModelContains(name);
        final var payload = jsonService.toString(result);
        fileService.uploadFile(getFileName("search_%s".formatted(name)), payload);
        saveHistory(payload);
        return result;
    }

    @Override
    public Car create(CreateCarDto createCarDto) {
        final var make = makeRepository.findByName(createCarDto.getMake());
        if (make == null) {
            throw new RuntimeException("Make not found: " + createCarDto.getMake());
        }
        final var car = buildCar(createCarDto, make);
        final var saved = carRepository.save(car);
        final var payload = jsonService.toString(saved);
        fileService.uploadFile(getFileName("create"), payload);
        saveHistory(payload);
        return saved;
    }

    @Override
    public void delete(DeleteCarDto deleteCarDto) {
        final var car = carRepository.findCarByModelAndMakeName(deleteCarDto.getModel(), deleteCarDto.getMake());
        if (car == null) {
            throw new RuntimeException("Car not found: make=%s model=%s".formatted(deleteCarDto.getMake(), deleteCarDto.getModel()));
        }
        carRepository.deleteById(car.getId());
        final var payload = jsonService.toString(car);
        fileService.uploadFile(getFileName("delete_%s_%s".formatted(deleteCarDto.getMake(), deleteCarDto.getModel())), payload);
        saveHistory(payload);
    }

    private static Car buildCar(CreateCarDto createCarDto, Make make) {
        final var car = Car.builder()
                .model(createCarDto.getModel())
                .year(createCarDto.getYear())
                .version(createCarDto.getVersion())
                .engine_pistons(createCarDto.getEnginePistons())
                .engine_volume(createCarDto.getEngineVolume())
                .engine_power(createCarDto.getEnginePower())
                .make(make)
                .build();
        return car;
    }

    private static String getFileName(String action) {
        return System.nanoTime() + "_" + action + ".json";
    }


    private void saveHistory(String carsPayload) {
        final var uuid = UUID.randomUUID().toString();

        History history = History.builder()
                .id(uuid)
                .payload(carsPayload)
                .build();
        mongoHistoryRepository.save(history);

        CosmosHistory cosmosHistory = CosmosHistory.builder()
                .id(uuid)
                .payload(carsPayload)
                .build();
        cosmosHistoryRepository.save(cosmosHistory);
    }
}
