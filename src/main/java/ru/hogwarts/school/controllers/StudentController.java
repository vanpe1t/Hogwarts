package ru.hogwarts.school.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.StudentDTO;
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

        @GetMapping(path = "/searchbyage")
        public ResponseEntity<List<StudentDTO>> getStudentByAge(@RequestParam Integer minAge, @RequestParam(required = false) Integer maxAge) {
            if (maxAge==null) {
                return ResponseEntity.ok(studentService.getStudentByAge(minAge));
            } else {
                return ResponseEntity.ok(studentService.getStudentByAgeBetween(minAge, maxAge));
            }
        }

        @PostMapping
        public StudentDTO createStudent(@RequestBody Student student) {
            return studentService.createStudent(student);
        }

        @DeleteMapping("{id}")
        public ResponseEntity deleteStudent(@PathVariable Long id)  {
            studentService.deleteStudent(id);
            return ResponseEntity.ok().build();
        }

        @PutMapping
        public ResponseEntity<StudentDTO> editStudent(@RequestBody Student student) {

            StudentDTO editingStudent = studentService.editStudent(student);

            if (editingStudent == null) {
                return  ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(editingStudent);
        }

}
