package com.bob.azure.repository.jpa;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bob.azure.entity.jpa.Make;

@Repository
public interface MakeRepository extends CrudRepository<Make, Integer> {
}
