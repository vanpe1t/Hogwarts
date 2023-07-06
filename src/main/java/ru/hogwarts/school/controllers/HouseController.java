package ru.hogwarts.school.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.models.Faculty;
import ru.hogwarts.school.services.HouseService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("faculty")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {

        Faculty faculty  = houseService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return houseService.createFaculty(faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFaculty(@PathVariable Long id)  {
        houseService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {

        Faculty editFaculty = houseService.editFaculty(faculty);
        if (editFaculty == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(editFaculty);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<Faculty>> getFacultyByColor(@RequestParam String color) {
        return ResponseEntity.ok(houseService.getFacultyByColor(color));
    }
}
