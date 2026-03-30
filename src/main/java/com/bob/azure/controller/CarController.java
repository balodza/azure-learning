package com.bob.azure.controller;


import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bob.azure.dto.CartDto;
import com.bob.azure.dto.CreateCarDto;
import com.bob.azure.dto.DeleteCarDto;
import com.bob.azure.mapper.CarMapper;
import com.bob.azure.service.CarService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
@Slf4j
public class CarController {
    private final CarService carService;
    private final CarMapper carMapper;

    @GetMapping
    public List<CartDto> getAll() {
        log.info("getAll() method called");
        return carMapper.toCarDtoList(carService.getCars());
    }

    @GetMapping("/{id}")
    public CartDto getById(@PathVariable("id") int id) {
        log.info("getById() method called, id: {}", id);
        return carMapper.toCarDto(carService.getById(id));
    }

    @GetMapping("/search")
    public List<CartDto> search(@RequestParam("name") String name) {
        log.info("search() method called, name: {}", name);
        return carMapper.toCarDtoList(carService.search(name));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartDto create(@Valid @RequestBody CreateCarDto createCarDto) {
        log.info("create() method called");
        return carMapper.toCarDto(carService.create(createCarDto));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Valid @RequestBody DeleteCarDto deleteCarDto) {
        log.info("delete() method called, make: {}, model: {}", deleteCarDto.getMake(), deleteCarDto.getModel());
        carService.delete(deleteCarDto);
    }
}


