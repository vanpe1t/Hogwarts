package ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.models.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.ArrayList;
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

    public StudentDTO createStudent(Student student) {
        return StudentDTO.fromStudent(studentRepository.save(student));
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }

    public StudentDTO editStudent(Student student) {
        return StudentDTO.fromStudent(studentRepository.save(student));

    }

    public void deleteStudent(long id) {
        //return studentMap.remove(id);
        studentRepository.deleteById(id);
    }

    public  List<StudentDTO> getStudentByAge(int age) {

        return returnDTOList(studentRepository.findStudentsByAge(age));

    }

    public List<StudentDTO> getStudentByAgeBetween(int minAge, int maxAge) {
        return  returnDTOList( studentRepository.findStudentsByAgeBetween(minAge, maxAge));
    }

    private List<StudentDTO> returnDTOList(List<Student> students) {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student: students) {
            studentDTOS.add(StudentDTO.fromStudent(student));
        }
        return studentDTOS;
    }


}
