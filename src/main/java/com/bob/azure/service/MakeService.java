package com.bob.azure.service;


import java.util.List;

import com.bob.azure.entity.mssql.Make;

public interface MakeService {
    List<Make> getGroups();

    Make create(String name);

    void delete(int id);
}
