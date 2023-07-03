package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private Long lastId = 0L;
    private final HashMap<Long, Student> studentMap = new HashMap<>();

    public Student CreateStudent(Student student) {
        student.setId(++lastId);
        studentMap.put(lastId, student);
        return student;
    }

    public Student FindStudent(long id) {
        return studentMap.get(id);
    }

    public Student editStudent(Student student) {
        if (studentMap.containsKey(student.getId())) {
            studentMap.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudent(long id) {
        return studentMap.remove(id);
    }

    public Map<Long, Student> getStudentByAge(int age) {

        return studentMap.entrySet()
                         .stream()
                         .filter(x -> x.getValue().getAge() == age)
                         .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
