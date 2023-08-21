package com.example.demoViettel2.Service;

import com.example.demoViettel2.Dto.StudentDTO;
import com.example.demoViettel2.Entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    Student creatNewStudent (StudentDTO student);

    void deleteStudent (Long id);

    Student updateStudent (Student studentUpdate);
    List<Student> getAllStudent();

    Optional<Student> searchStudent (Long id);
    List<Student> searchStudents(String searchText);

    List<Student> searchStudentByField (Student studentSearch);
    public boolean isEmailDuplicated(Student student);

    public Page<Student> getStudents(int page, int size);

    public List<Student> getAllStudentsSortedByIdDescending();
    public void addNewClassName(String className);
}
