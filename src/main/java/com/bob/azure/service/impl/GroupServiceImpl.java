package com.bob.azure.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.bob.azure.dto.Group;
import com.bob.azure.dto.Student;
import com.bob.azure.repository.StudentRepository;
import com.bob.azure.service.GroupService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupServiceImpl implements GroupService {
    
    private final StudentRepository studentRepository;
    
    @Override
    public List<Group> getGroups() {
        return studentRepository.getAllStudents().stream()
                .map(Student::getGroup)
                .distinct()
                .toList();
    }
}
