package com.bob.azure.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bob.azure.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    Student getStudentById(int id);

    List<Student> getStudentsByFirstNameContains(String name);
}
