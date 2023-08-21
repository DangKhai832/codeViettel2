package com.example.demoViettel2.Dto;

import com.example.demoViettel2.Entity.Student;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String className;
    public Student toEntity() {
        Student student = new Student();
        student.setId(id);
        student.setClassName(className);
        student.setEmail(email);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        return student;
    }
}
