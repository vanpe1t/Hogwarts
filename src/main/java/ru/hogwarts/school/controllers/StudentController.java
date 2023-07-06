package ru.hogwarts.school.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.models.Student;
import ru.hogwarts.school.services.StudentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("student")
public class StudentController {

        private final StudentService studentService;

        public StudentController(StudentService studentService) {
            this.studentService = studentService;
        }

        @GetMapping("{id}")
        public ResponseEntity<Student> getStudent(@PathVariable Long id) {

            Student student = studentService.findStudent(id);

            if (student == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(student);

        }

        @GetMapping(path = "/list")
        public ResponseEntity<List<Student>> getStudentByAge(@RequestParam int age) {
            return ResponseEntity.ok(studentService.getStudentByAge(age));
        }

        @PostMapping
        public Student createStudent(@RequestBody Student student) {
            return studentService.createStudent(student);
        }

        @DeleteMapping("{id}")
        public ResponseEntity deleteStudent(@PathVariable Long id)  {
            studentService.deleteStudent(id);
            return ResponseEntity.ok().build();
        }

        @PutMapping
        public ResponseEntity<Student> editStudent(@RequestBody Student student) {

            Student editingStudent = studentService.editStudent(student);

            if (editingStudent == null) {
                return  ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(editingStudent);
        }

}
