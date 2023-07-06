package ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.models.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private Long lastId = 0L;
    //private final HashMap<Long, Student> studentMap = new HashMap<>();
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
//        student.setId(++lastId);
//        studentMap.put(lastId, student);
//        return student;
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
//        return studentMap.get(id);
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
//        if (studentMap.containsKey(student.getId())) {
//            studentMap.put(student.getId(), student);
//            return student;
//        }
//        return null;
    }

    public void deleteStudent(long id) {
        //return studentMap.remove(id);
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentByAge(int age) {

        return studentRepository.findStudentsByAge(age);

//        return studentMap.entrySet()
//                         .stream()
//                         .filter(x -> x.getValue().getAge() == age)
//                         .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
