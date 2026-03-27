package com.bob.azure.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

class JsonServiceImplTest {

    private JsonServiceImpl jsonService;

    @BeforeEach
    void setUp() {
        jsonService = new JsonServiceImpl(new ObjectMapper());
    }

    @Test
    void toString_convertsSimpleObjectToJson() {
        var result = jsonService.toString(Map.of("id", 1, "name", "Ford"));

        assertThat(result).contains("\"id\"").contains("\"name\"").contains("Ford");
    }

    @Test
    void toString_convertsListToJsonArray() {
        var result = jsonService.toString(List.of("a", "b"));

        assertThat(result).isEqualTo("[\"a\",\"b\"]");
    }

    @Test
    void toString_convertsNullToJsonNull() {
        var result = jsonService.toString(null);

        assertThat(result).isEqualTo("null");
    }

    @Test
    void toString_whenObjectMapperThrows_propagatesException() {
        var failingMapper = mock(ObjectMapper.class);
        when(failingMapper.writeValueAsString(any())).thenThrow(new JacksonException("serialization failed") {});
        var service = new JsonServiceImpl(failingMapper);

        assertThatThrownBy(() -> service.toString("any"))
                .isInstanceOf(JacksonException.class);
    }
}
