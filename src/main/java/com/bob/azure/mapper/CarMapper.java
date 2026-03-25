package com.bob.azure.mapper;


import java.util.List;

import org.mapstruct.Mapper;

import com.bob.azure.dto.CartDto;
import com.bob.azure.entity.Car;

@Mapper(componentModel = "spring", uses = {CarMapper.class})
public interface CarMapper {
    CartDto toCarDto(Car car);

    List<CartDto> toCarDtoList(List<Car> cars);
}
