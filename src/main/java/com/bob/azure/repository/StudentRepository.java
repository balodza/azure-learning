package com.bob.azure.repository;


import java.util.List;

import com.bob.azure.entity.Student;

public interface StudentRepository {

    List<Student> getAllStudents();

    Student getStudentById(int id);

    List<Student> search(String name);
}
