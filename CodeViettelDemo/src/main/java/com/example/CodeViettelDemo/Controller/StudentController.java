package com.example.CodeViettelDemo.Controller;

import com.example.CodeViettelDemo.entity.Student;
import com.example.CodeViettelDemo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Thay thế @Controller bằng @RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public Student createNewStudent(@RequestBody Student student) { // Không cần ResponseEntity, trả về Student trực tiếp
        return studentService.creatNewStudent(student);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Thay thế ResponseEntity<Void> bằng @ResponseStatus
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() { // Không cần ResponseEntity, trả về List<Student> trực tiếp
        return studentService.getAllStudent();
    }

    @GetMapping("/class/{classId}")
    public List<Student> getAllStudentsByClassID(@PathVariable Long classId) { // Không cần ResponseEntity, trả về List<Student> trực tiếp
        return studentService.getAllStudentByClassID(classId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> searchStudent(@PathVariable Long id) {
        Optional<Student> student = studentService.searchStudent(id);
        return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestBody Student studentUpdate) { // Không cần ResponseEntity, trả về Student trực tiếp
        return studentService.updateStudent(studentUpdate);
    }
}
