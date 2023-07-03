package ru.hogwarts.school.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Map;

@RestController
@RequestMapping("student")
public class StudentController {

        private final StudentService studentService;

        public StudentController(StudentService studentService) {
            this.studentService = studentService;
        }

        @GetMapping("{id}")
        public ResponseEntity<Student> GetStudent(@PathVariable Long id) {

            Student student = studentService.FindStudent(id);

            if (student == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(student);

        }

        @GetMapping(path = "/list")
        public ResponseEntity<Map<Long, Student>> GetStudentByAge(@RequestParam int age) {
            return ResponseEntity.ok(studentService.getStudentByAge(age));
        }

        @PostMapping
        public Student CreateStudent(@RequestBody Student student) {
            return studentService.CreateStudent(student);
        }

        @DeleteMapping("{id}")
        public Student DeleteStudent(@PathVariable Long id)  {
            return studentService.deleteStudent(id);
        }

        @PutMapping
        public ResponseEntity<Student> EditStudent(@RequestBody Student student) {

            Student editingStudent = studentService.editStudent(student);

            if (editingStudent == null) {
                return  ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(editingStudent);
        }

}
