package com.bob.azure.service;


import java.util.List;

import com.bob.azure.entity.Student;

public interface StudentService {
    List<Student> getStudents();

    Student getById(int id);

    List<Student> search(String name);
}
