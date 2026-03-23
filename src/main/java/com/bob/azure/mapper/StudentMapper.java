package com.bob.azure.mapper;


import java.util.List;

import org.mapstruct.Mapper;

import com.bob.azure.dto.StudentDto;
import com.bob.azure.entity.Student;

@Mapper(componentModel = "spring", uses = {StudentMapper.class})
public interface StudentMapper {
    StudentDto toStudentDto(Student student);

    List<StudentDto> toStudentDtoList(List<Student> students);
}
