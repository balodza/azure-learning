package com.bob.azure.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bob.azure.dto.GroupDto;
import com.bob.azure.mapper.GroupMapper;
import com.bob.azure.service.GroupService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/groups")
@Slf4j
@RequiredArgsConstructor
public class GroupController {
    private final GroupMapper groupMapper;
    private final GroupService groupService;

    @GetMapping
    public List<GroupDto> getAll() {
        log.info("getAll() method called");
        return groupMapper.toGroupDtoList(groupService.getGroups());
    }

}
