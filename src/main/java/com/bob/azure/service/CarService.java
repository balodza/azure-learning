package com.bob.azure.service;


import java.util.List;

import com.bob.azure.entity.Car;

public interface CarService {
    List<Car> getStudents();

    Car getById(int id);

    List<Car> search(String name);
}
