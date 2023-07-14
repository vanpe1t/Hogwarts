package ru.hogwarts.school.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.models.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacultyService {
    private Long lastId = 0L;
    //private HashMap<Long, Faculty> facultyMap = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public FacultyDTO createFaculty(Faculty faculty) {
        logger.debug("Was invoked method for create faculty {}", faculty.toString());
        return FacultyDTO.fromFaculty(facultyRepository.save(faculty));

    }

    public Faculty findFaculty(long id) {
        logger.debug("Was invoked method for find faculty by id {}", id);
        return facultyRepository.findById(id).get();
    }

    public FacultyDTO editFaculty(Faculty faculty) {
        logger.debug("Was invoked method for edit faculty {}", faculty.toString());
        return FacultyDTO.fromFaculty(facultyRepository.save(faculty));
   }

    public void deleteFaculty(long id) {
        logger.debug("Was invoked method for delete faculty by id {}", id);
        facultyRepository.deleteById(id);
    }

    public List<FacultyDTO> getFacultyByColor(String color) {
        logger.debug("Was invoked method for get faculties by color {}", color);
        List<Faculty>faculties = facultyRepository.findFacultiesByColor(color);
        List<FacultyDTO> facultyDTOs = new ArrayList<>();
        for (Faculty faculty: faculties) {
            facultyDTOs.add(FacultyDTO.fromFaculty(faculty));
        }
        logger.debug("Get list {}", facultyDTOs);
        return facultyDTOs;
   }
}
