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


        @GetMapping(path = "/findallstudent")
        public ResponseEntity<List<StudentDTO>> findAllStudent(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
            return  ResponseEntity.ok(studentService.findAllStudent(page, size));
        }

        @GetMapping("{id}")
        public ResponseEntity<StudentDTO> getStudent(@PathVariable long id) {

            StudentDTO student = studentService.findStudent(id);

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

        @GetMapping(path = "/get_quantity_of_student")
        public ResponseEntity<Integer> getQuantityOfStudent() {

            return ResponseEntity.ok(studentService.getQuantityOfStudent());

        }

        @GetMapping(path = "/get_average_age")
        public ResponseEntity<Double> getAverageAge() {

            return ResponseEntity.ok(studentService.getAverageAge());

        }

        @GetMapping(path = "/get_youngest_student")
        public ResponseEntity<List<StudentDTO>> getYoungestStudent() {

            return ResponseEntity.ok(studentService.getYoungestStudent());

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
