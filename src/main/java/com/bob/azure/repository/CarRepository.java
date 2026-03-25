package com.bob.azure.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bob.azure.entity.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {

    Car getStudentById(int id);

    List<Car> getCarsByModelContains(String name);
}
