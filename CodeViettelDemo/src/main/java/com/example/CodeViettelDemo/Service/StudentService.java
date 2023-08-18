package com.example.CodeViettelDemo.Service;

import com.example.CodeViettelDemo.entity.Student;
import com.example.CodeViettelDemo.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService{
    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student creatNewStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllStudent() {
        List<Student> students;
        students = (List<Student>) studentRepository.findAll();
        return students;
    }

    @Override
    public List<Student> getAllStudentByClassID(Long classId) {
        List<Student> students;
        students = (List<Student>) studentRepository.findAll();
        List<Student> studentsByClassID = new ArrayList<>();
        for (Student student : students) {
            if (student.getClassId() == classId) {
                studentsByClassID.add(student);
            }
        }
        return  studentsByClassID;
    }

    @Override
    public Optional<Student> searchStudent(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student updateStudent(Student studentUpdate) {
        Student student = studentRepository.findById(studentUpdate.getId()).orElseThrow(
            ()->new IllegalArgumentException("Không tìm thấy học sinh.")
        );
        student.setFirstName(studentUpdate.getFirstName());
        student.setLastName(studentUpdate.getLastName());
        student.setClassId(studentUpdate.getClassId());
        student.setEmailId(studentUpdate.getEmailId());
        return student;
    }

}
