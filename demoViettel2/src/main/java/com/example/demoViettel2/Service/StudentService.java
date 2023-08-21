package com.example.demoViettel2.Service;

import com.example.demoViettel2.Dto.StudentDTO;
import com.example.demoViettel2.Entity.Student;
import com.example.demoViettel2.Repository.iStudentRepository;
import com.example.demoViettel2.Repository.iStudentRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {
    @Autowired
    iStudentRepository studentRepository;
    @Autowired
    iStudentRepositoryImpl iStudentRepositoryImpl;

    @Override
    public Student creatNewStudent(StudentDTO student) {
        return studentRepository.save(student.toEntity());
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(   id);
    }

    @Override
    public List<Student> getAllStudent() {
        List<Student> students;
        students = studentRepository.findAll();
        return students;
    }

    @Override
    public Optional<Student> searchStudent(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student updateStudent(Student studentUpdate) {
        Student student = studentRepository.findById(studentUpdate.getId()).orElseThrow(
                () -> new IllegalArgumentException("Không tìm thấy học sinh.")
        );
        student.setFirstName(studentUpdate.getFirstName());
        student.setLastName(studentUpdate.getLastName());
        student.setClassName(studentUpdate.getClassName());
        student.setEmail(studentUpdate.getEmail());
        return studentRepository.save(student);
    }
    @Override
    public List<Student> searchStudents(String searchText) {
        return studentRepository.findStudentsByMultipleFields(searchText);
    }

    @Override
    public List<Student> searchStudentByField(Student studentSearch) {
        return iStudentRepositoryImpl.findStudentByFieldSql(studentSearch);
    }

    @Override
    public boolean isEmailDuplicated(Student student) {
        return studentRepository.existsByEmailAndClassName(student.getEmail(),student.getClassName());
    }

//    @Override
//    public List<classStudent> getClassStudent() {
//        List<classStudent> classStudents;
//        classStudents = studentRepository.getClassStudent();
//        return classStudents;
//    }
    @Override
    public Page<Student> getStudents(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return studentRepository.findAll(pageable);
    }

    @Override
    public List<Student> getAllStudentsSortedByIdDescending() {
        return studentRepository.findAllByOrderByidDesc();
    }

    @Override
    public void addNewClassName(String className) {
        studentRepository.addNewClassName(className);
    }

}


