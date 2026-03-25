package com.bob.azure.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bob.azure.entity.Make;

@Repository
public interface MakeRepository extends CrudRepository<Make, Integer> {
}
