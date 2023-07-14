package ru.hogwarts.school.services;

import org.hibernate.loader.entity.NaturalIdEntityJoinWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDTO createStudent(Student student) {
        logger.debug("Was invoked method for create student {}", student.toString());
        return StudentDTO.fromStudent(studentRepository.save(student));
    }

    public StudentDTO findStudent(long id) {
        logger.debug("Was invoked method for find student with id = {}", id);
        return StudentDTO.fromStudent(studentRepository.findById(id).get());
    }

    public StudentDTO editStudent(Student student) {
        return StudentDTO.fromStudent(studentRepository.save(student));
    }

    public void deleteStudent(long id) {
        logger.debug("Was invoked method for delete student with id = {}", id);
        studentRepository.deleteById(id);
    }

    public  List<StudentDTO> getStudentByAge(int age) {

        return returnDTOList(studentRepository.findStudentsByAge(age));

    }

    public List<StudentDTO> getStudentByAgeBetween(int minAge, int maxAge) {
        logger.debug("Was invoked method for get student by age min age {}, max age {} ", minAge, maxAge);
        return  returnDTOList( studentRepository.findStudentsByAgeBetween(minAge, maxAge));
    }

    public Integer getQuantityOfStudent() {
        logger.debug("Was invoked method get quantity of student");
        return studentRepository.getQuantityOfStudent();
    }

    public Double getAverageAge() {
        logger.debug("Was invoked method for get average age");
        return studentRepository.getAverageAge();
    }

    public List<StudentDTO> getYoungestStudent() {
        logger.debug("Was invoked method for get youngest student");
        List<StudentDTO>  studentDTOS = returnDTOList(studentRepository.getYoungestStudent());
        logger.debug("Get list {}", studentDTOS);
        return studentDTOS;
    }

    public List<StudentDTO> findAllStudent(Integer pageNumber, Integer pageSize) {
        logger.debug("Was invoked method for find all student");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize > 50 || pageSize <=0 ? 50 : pageSize);
        List<StudentDTO> studentDTOS = returnDTOList(studentRepository.findAll(pageRequest).getContent());
        logger.debug("Get list {}", studentDTOS);
        return studentDTOS;
    }

    private List<StudentDTO> returnDTOList(List<Student> students) {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student: students) {
            studentDTOS.add(StudentDTO.fromStudent(student));
        }
        return studentDTOS;
    }


}
