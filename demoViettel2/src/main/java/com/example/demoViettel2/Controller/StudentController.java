package com.example.demoViettel2.Controller;

import com.example.demoViettel2.Dto.StudentDTO;
import com.example.demoViettel2.Entity.Api.ApiModule;
import com.example.demoViettel2.Entity.Student;
import com.example.demoViettel2.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public ApiModule createNewStudent(@RequestBody StudentDTO student) {
        studentService.addNewClassName(student.getClassName());
        Student createdStudent = studentService.creatNewStudent(student);
        return new ApiModule(200, "SUCCESS CREATE");
    }

    @DeleteMapping("/delete/{id}")
    public ApiModule deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ApiModule(200, "DELETE SUCCESS");
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() { // Không cần ResponseEntity, trả về List<Student> trực tiếp
        return studentService.getAllStudent();
    }


    @PostMapping("/getDetail")
    public ResponseEntity<Student> searchStudent(@RequestBody StudentDTO dto) {
        Optional<Student> student = studentService.searchStudent(dto.getId());
        return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping ("/{id}")
    public ResponseEntity<Student> searchStudent(@PathVariable Long id) {
        Optional<Student> student = studentService.searchStudent(id);
        return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestBody Student studentUpdate) {
        return studentService.updateStudent(studentUpdate);
    }

    @PostMapping("/search/{searchText}")
    public ResponseEntity<List<Student>> searchStudents(@PathVariable String searchText) {
        List<Student> students = studentService.searchStudents(searchText);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/searchByField")
    public ResponseEntity<List<Student>> searchStudentByField(@RequestBody Student studentSearch) {
        return ResponseEntity.of(Optional.of(studentService.searchStudentByField(studentSearch)));
    }

    @GetMapping("/students")
    public ResponseEntity<Page<Student>> getStudents(@RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "5") int size) {
        // Trước tiên, thực hiện truy vấn và sắp xếp tất cả học sinh
        List<Student> allStudents = studentService.getAllStudentsSortedByIdDescending();

        // Sau đó, tạo đối tượng Page từ danh sách đã sắp xếp
        Page<Student> studentsPage = createPageFromList(allStudents, page, size);

        return ResponseEntity.ok(studentsPage);
    }

    private Page<Student> createPageFromList(List<Student> students, int page, int size) {
        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, students.size());

        List<Student> pageContent = students.subList(startIndex, endIndex);
        return new PageImpl<>(pageContent, PageRequest.of(page - 1, size), students.size());
    }
}
