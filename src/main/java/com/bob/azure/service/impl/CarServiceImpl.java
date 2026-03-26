package com.bob.azure.service.impl;


import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import com.bob.azure.entity.Car;
import com.bob.azure.repository.CarRepository;
import com.bob.azure.service.FileService;
import com.bob.azure.service.CarService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final FileService fileService;

    private final JsonService jsonService;

    @Override
    public List<Car> getCars() {
        final var cars = carRepository.findAll();
        fileService.uploadFile(getFileName("getCars"), jsonService.toString(cars));
        return IterableUtils.toList(cars);
    }

    @Override
    public Car getById(int id) {
        final var carById = carRepository.getCarById(id);
        fileService.uploadFile(getFileName("getById_%s".formatted(id)), jsonService.toString(carById));
        return carById;
    }

    @Override
    public List<Car> search(String name) {
        final var result = carRepository.getCarsByModelContains(name);
        fileService.uploadFile(getFileName("search_%s".formatted(name)), jsonService.toString(result));
        return result;
    }

    private static String getFileName(String action) {
        return System.nanoTime() + "_" + action + ".json";
    }

}
