package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.HouseService;
import ru.hogwarts.school.service.StudentService;

import java.util.Map;

@RestController
@RequestMapping("faculty")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> GetFaculty(@PathVariable Long id) {

        Faculty faculty  = houseService.FindFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty CreateFaculty(@RequestBody Faculty faculty) {
        return houseService.CreateFaculty(faculty);
    }

    @DeleteMapping("{id}")
    public Faculty DeleteFaculty(@PathVariable Long id)  {
        return houseService.deleteFaculty(id);
    }

    @PutMapping
    public ResponseEntity<Faculty> EditFaculty(@RequestBody Faculty faculty) {

        Faculty editFaculty = houseService.editFaculty(faculty);
        if (editFaculty == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(editFaculty);
    }

    @GetMapping(path = "/list")
    public ResponseEntity< Map<Long, Faculty>> GetFacultyByColor(@RequestParam String color) {
        return ResponseEntity.ok(houseService.getFacultyByColor(color));
    }
}
