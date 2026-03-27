package com.bob.azure.controller;


import java.util.List;

import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bob.azure.dto.MakeDto;
import com.bob.azure.mapper.MakeMapper;
import com.bob.azure.service.MakeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Validated
@RestController
@RequestMapping("/makes")
@Slf4j
@RequiredArgsConstructor
public class MakeController {
    private final MakeMapper makeMapper;
    private final MakeService makeService;

    @GetMapping
    public List<MakeDto> getAll() {
        log.info("getAll() method called");
        return makeMapper.toMakeDtoList(makeService.getGroups());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MakeDto create(@NotBlank @RequestParam("name") String name) {
        log.info("create() method called, name: {}", name);
        return makeMapper.toMakeDto(makeService.create(name));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        log.info("delete() method called, id: {}", id);
        makeService.delete(id);
    }

}
