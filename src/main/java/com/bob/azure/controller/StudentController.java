package com.bob.azure.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bob.azure.dto.StudentDto;
import com.bob.azure.mapper.StudentMapper;
import com.bob.azure.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Slf4j
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @GetMapping
    public List<StudentDto> getAll() {
        log.info("getAll() method called");
        return studentMapper.toStudentDtoList(studentService.getStudents());
    }

    @GetMapping("/{id}")
    public StudentDto getById(@PathVariable("id") int id) {
        log.info("getById() method called, id: {}", id);
        return studentMapper.toStudentDto(studentService.getById(id));
    }

    @GetMapping("/search")
    public List<StudentDto> search(@RequestParam("name") String name) {
        log.info("search() method called, name: {}", name);
        return studentMapper.toStudentDtoList(studentService.search(name));
    }
}


