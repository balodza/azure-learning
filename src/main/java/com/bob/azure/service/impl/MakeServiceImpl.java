package com.bob.azure.service.impl;


import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import com.bob.azure.entity.mssql.Make;
import com.bob.azure.repository.mssql.CarRepository;
import com.bob.azure.repository.mssql.MakeRepository;
import com.bob.azure.service.MakeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MakeServiceImpl implements MakeService {

    private final MakeRepository makeRepository;

    private final CarRepository carRepository;

    @Override
    public List<Make> getGroups() {
        return IterableUtils.toList(makeRepository.findAll());
    }

    @Override
    public Make create(String name) {
        final var make = Make.builder()
                .name(name)
                .build();
        return makeRepository.save(make);
    }

    @Override
    public void delete(int id) {
        final var cars = carRepository.findCarsByMakeId(id);
        carRepository.deleteAll(cars);
        makeRepository.deleteById(id);
    }
}
