package com.bob.azure.service.impl;


import java.util.List;

import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import com.bob.azure.dto.Student;
import com.bob.azure.repository.StudentRepository;
import com.bob.azure.service.FileService;
import com.bob.azure.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRepository;
    
    private final FileService fileService;
    
    @Override
    public List<Student> getStudents() {
        fileService.uploadFile(getFileName("getStudents"), "getStudents()");
        return studentRepository.getAllStudents();
    }
    
    @Override
    public Student getById(int id) {
        fileService.uploadFile(getFileName("getById"), "getById(%s)".formatted(id));
        return studentRepository.getStudentById(id);
    }

    @Override
    public List<Student> search(String name) {
        fileService.uploadFile(getFileName("search"), "search(%s)".formatted(name));
        return studentRepository.search(name);
    }

    private static String getFileName(String action) {
        return System.nanoTime() + "_" + action;
    }

}
