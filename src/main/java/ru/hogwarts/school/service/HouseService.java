package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HouseService {
    private Long lastId = 0L;
    private HashMap<Long, Faculty> facultyMap = new HashMap<>();

    public Faculty CreateFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        facultyMap.put(lastId, faculty);
        return faculty;
    }

    public Faculty FindFaculty(long id) {
        return facultyMap.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (facultyMap.containsKey(faculty.getId())) {
            facultyMap.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFaculty(long id) {
        return facultyMap.remove(id);
    }

    public Map<Long, Faculty> getFacultyByColor(String color) {

        return facultyMap.entrySet()
                .stream()
                .filter(x -> x.getValue().getColor().equals(color))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
   }

    //public HashMap<>

}
