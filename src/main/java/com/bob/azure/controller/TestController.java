package com.bob.azure.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bob.azure.dto.Student;
import com.bob.azure.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/students")
@Slf4j
@RequiredArgsConstructor
public class TestController {
    private final StudentService studentService;

    @GetMapping
    public List<Student> getAll() {
        log.info("getAll() method called");
        return studentService.getStudents();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable("id") int id) {
        log.info("getById() method called, id: {}", id);
        return studentService.getById(id);
    }
    
    @GetMapping("/search")
    public List<Student> search(@RequestParam("name") String name) {
        log.info("search() method called, name: {}", name);
        return studentService.search(name);
    }
}
