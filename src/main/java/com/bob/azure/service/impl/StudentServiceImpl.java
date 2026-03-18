package com.bob.azure.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.bob.azure.dto.Student;
import com.bob.azure.repository.StudentRepository;
import com.bob.azure.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    
    @Override
    public List<Student> getStudents() {
        return studentRepository.getAllStudents();
    }

    @Override
    public Student getById(int id) {
        return studentRepository.getStudentById(id);
    }

    @Override
    public List<Student> search(String name) {
        return studentRepository.search(name);
    }

}
