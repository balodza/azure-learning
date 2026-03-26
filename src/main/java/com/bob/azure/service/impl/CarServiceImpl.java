package com.bob.azure.service.impl;


import java.util.List;
import java.util.UUID;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import com.bob.azure.entity.mongo.History;
import com.bob.azure.repository.mongo.HistoryRepository;
import com.bob.azure.entity.jpa.Car;
import com.bob.azure.repository.jpa.CarRepository;
import com.bob.azure.service.FileService;
import com.bob.azure.service.CarService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    
    private final HistoryRepository historyRepository;

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

    private static String getFileName(String action) {
        return System.nanoTime() + "_" + action + ".json";
    }


    private void saveHistory(String carsPayload) {
        History history = History.builder()
                .id(UUID.randomUUID().toString())
                .payload(carsPayload)
                .build();
        historyRepository.save(history);
    }
}
