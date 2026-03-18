package com.bob.azure.service.impl;


import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class JsonServiceImpl implements JsonService {
    
    private final ObjectMapper objectMapper;
    
    @Override
    public String toString(Object o) {
        return objectMapper.writeValueAsString(o);
    }
}
