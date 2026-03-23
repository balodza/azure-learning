package com.bob.azure.service.impl;


import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import com.bob.azure.entity.Student;
import com.bob.azure.repository.StudentRepository;
import com.bob.azure.service.FileService;
import com.bob.azure.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final FileService fileService;

    private final JsonService jsonService;

    @Override
    public List<Student> getStudents() {
        final var allStudents = studentRepository.findAll();
        fileService.uploadFile(getFileName("getStudents"), jsonService.toString(allStudents));
        return IterableUtils.toList(allStudents);
    }

    @Override
    public Student getById(int id) {
        final var studentById = studentRepository.getStudentById(id);
        fileService.uploadFile(getFileName("getById_%s".formatted(id)), jsonService.toString(studentById));
        return studentById;
    }

    @Override
    public List<Student> search(String name) {
        final var result = studentRepository.getStudentsByFirstNameContains(name);
        fileService.uploadFile(getFileName("search_%s".formatted(name)), jsonService.toString(result));
        return result;
    }

    private static String getFileName(String action) {
        return System.nanoTime() + "_" + action + ".json";
    }

}
