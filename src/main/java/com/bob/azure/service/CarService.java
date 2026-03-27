package com.bob.azure.service;


import java.util.List;

import com.bob.azure.entity.mssql.Car;

public interface CarService {
    List<Car> getCars();

    Car getById(int id);

    List<Car> search(String name);
}
