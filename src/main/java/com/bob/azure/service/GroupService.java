package com.bob.azure.service;


import java.util.List;

import com.bob.azure.entity.mssql.Make;

public interface GroupService {
    List<Make> getGroups();
}
