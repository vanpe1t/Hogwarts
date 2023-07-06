package ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.models.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HouseService {
    private Long lastId = 0L;
    //private HashMap<Long, Faculty> facultyMap = new HashMap<>();

    private final FacultyRepository facultyRepository;

    public HouseService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {

        return facultyRepository.save(faculty);
//        faculty.setId(++lastId);
//        facultyMap.put(lastId, faculty);
//        return faculty;
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
//        return facultyMap.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
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

    public List<Faculty> getFacultyByColor(String color) {
        return facultyRepository.findFacultiesByColor(color);
//        return facultyMap.entrySet()
//                .stream()
//                .filter(x -> x.getValue().getColor().equals(color))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
   }

    //public HashMap<>

}
