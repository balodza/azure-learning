package com.bob.azure.repository.jpa;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bob.azure.entity.jpa.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {

    Car getCarById(int id);

    List<Car> getCarsByModelContains(String name);
}
