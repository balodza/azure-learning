package com.bob.azure.mapper;


import java.util.List;

import org.mapstruct.Mapper;

import com.bob.azure.dto.GroupDto;
import com.bob.azure.entity.Group;

@Mapper(componentModel = "spring", uses = {StudentMapper.class})
public interface GroupMapper {
    GroupDto toGroupDto(Group group);

    List<GroupDto> toGroupDtoList(List<Group> groups);
}
