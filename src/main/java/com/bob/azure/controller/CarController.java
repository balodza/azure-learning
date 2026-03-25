package com.bob.azure.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bob.azure.dto.CartDto;
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
        return carMapper.toCarDtoList(carService.getStudents());
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
}


