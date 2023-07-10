package ru.hogwarts.school.services;

import org.hibernate.loader.entity.NaturalIdEntityJoinWalker;
import org.springframework.data.domain.PageRequest;
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

    public Integer  getQuantityOfStudent() {
        return studentRepository.getQuantityOfStudent();
    }

    public Double getAverageAge() {
        return studentRepository.getAverageAge();
    }

    public List<StudentDTO> getYoungestStudent() {
        return returnDTOList(studentRepository.getYoungestStudent());
    }

    public List<StudentDTO> findAllStudent(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize > 50 || pageSize <=0 ? 50 : pageSize);
        return returnDTOList(studentRepository.findAll(pageRequest).getContent());
    }

    private List<StudentDTO> returnDTOList(List<Student> students) {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student: students) {
            studentDTOS.add(StudentDTO.fromStudent(student));
        }
        return studentDTOS;
    }


}
