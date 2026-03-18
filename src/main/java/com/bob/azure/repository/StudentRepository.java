package com.bob.azure.repository;


import java.util.List;

import com.bob.azure.dto.Student;

public interface StudentRepository {

    List<Student> getAllStudents();
    
    Student getStudentById(int id);

    Student search(String name);
}
