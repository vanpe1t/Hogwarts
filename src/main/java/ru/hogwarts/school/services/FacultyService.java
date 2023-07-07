package ru.hogwarts.school.services;

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

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public FacultyDTO createFaculty(Faculty faculty) {

        return FacultyDTO.fromFaculty(facultyRepository.save(faculty));

    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
//        return facultyMap.get(id);
    }

    public FacultyDTO editFaculty(Faculty faculty) {
        return FacultyDTO.fromFaculty(facultyRepository.save(faculty));
//        if (facultyMap.containsKey(faculty.getId())) {
//            facultyMap.put(faculty.getId(), faculty);
//            return faculty;
//        }
//        return null;
   }

    public void deleteFaculty(long id) {
//        return facultyMap.remove(id);
        facultyRepository.deleteById(id);
    }

    public List<FacultyDTO> getFacultyByColor(String color) {
        List<Faculty>faculties = facultyRepository.findFacultiesByColor(color);
        List<FacultyDTO> facultyDTOs = new ArrayList<>();
        for (Faculty faculty: faculties) {
            facultyDTOs.add(FacultyDTO.fromFaculty(faculty));
        }
        return facultyDTOs;

//        return facultyMap.entrySet()
//                .stream()
//                .filter(x -> x.getValue().getColor().equals(color))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
   }

    //public HashMap<>

}
