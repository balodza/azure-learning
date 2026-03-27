package com.bob.azure.repository.mssql;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bob.azure.entity.mssql.Make;

@Repository
public interface MakeRepository extends CrudRepository<Make, Integer> {
}
