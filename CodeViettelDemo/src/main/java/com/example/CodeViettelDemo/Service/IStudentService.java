package com.example.CodeViettelDemo.Service;

import com.example.CodeViettelDemo.entity.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    Student creatNewStudent (Student student);

    void deleteStudent (Long id);

    Student updateStudent (Student studentUpdate);
    List<Student> getAllStudent();

    List<Student> getAllStudentByClassID(Long classId);

    Optional<Student> searchStudent (Long id);

}
