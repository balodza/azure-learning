package com.bob.azure.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bob.azure.dto.MakeDto;
import com.bob.azure.mapper.MakeMapper;
import com.bob.azure.service.GroupService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/makes")
@Slf4j
@RequiredArgsConstructor
public class MakeController {
    private final MakeMapper makeMapper;
    private final GroupService groupService;

    @GetMapping
    public List<MakeDto> getAll() {
        log.info("getAll() method called");
        return makeMapper.toMakeDtoList(groupService.getGroups());
    }

}
