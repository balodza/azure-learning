package com.bob.azure.mapper;


import java.util.List;

import org.mapstruct.Mapper;

import com.bob.azure.dto.MakeDto;
import com.bob.azure.entity.Make;

@Mapper(componentModel = "spring", uses = {CarMapper.class})
public interface MakeMapper {
    MakeDto toMakeDto(Make make);

    List<MakeDto> toMakeDtoList(List<Make> makes);
}
