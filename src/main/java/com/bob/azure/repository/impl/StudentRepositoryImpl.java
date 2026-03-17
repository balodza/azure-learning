package com.bob.azure.repository.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.bob.azure.dto.Group;
import com.bob.azure.dto.Student;
import com.bob.azure.repository.StudentRepository;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private final List<Student> dbStudents;

    {
        dbStudents = new ArrayList<>();
        dbStudents.add(getStudent(1, "Bob", "St", "bob@bob.bob", "PMI-12"));
        dbStudents.add(getStudent(2, "Taras", "Klum", "tkl@gmail.com", "PMI-12"));
        dbStudents.add(getStudent(3, "Yurik", "Fedya", "fedy@gmail.com", "PMI-12"));
        dbStudents.add(getStudent(3, "Yurik Duplicate", "Fedya", "fedy@gmail.com", "PMI-12"));
    }


    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(dbStudents);
    }

    @Override
    public Student getStudentById(int id) {
        final List<Student> candidates = dbStudents.stream()
                .filter(student -> student.getId() == id)
                .toList();
        if (candidates.isEmpty()) {
            return null;
        }
        if (candidates.size() > 1) {
            throw new IllegalStateException("Found more than 1 users with identical id: %s".formatted(id));
        }
        return candidates.getFirst();
    }

    private Student getStudent(int id, String fName, String lName, String email, String groupName) {
        return Student.builder()
                .id(id)
                .firstName(fName)
                .lastName(lName)
                .email(email)
                .group(getGroup(groupName))
                .build();
    }

    private Group getGroup(String name) {
        return Group.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .build();
    }
}
