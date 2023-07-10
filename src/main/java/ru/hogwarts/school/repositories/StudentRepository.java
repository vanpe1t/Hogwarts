package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.models.Student;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    public List<Student> findStudentsByAge(int age);

    public List<Student> findStudentsByAgeBetween(int minAge, int maxAge);

    @Query(value = "SELECT id, name, age, faculty_id FROM  student", nativeQuery = true)
    public List<Student> findAllStudent();

    @Query(value = "SELECT COUNT(*) as Quantity FROM  student", nativeQuery = true)
    public Integer getQuantityOfStudent();

    @Query(value = "SELECT AVG(age) as AvgAge FROM  student", nativeQuery = true)
    public Double getAverageAge();

    @Query(value = "SELECT id, name, age, faculty_id FROM  student ORDER BY age LIMIT 5", nativeQuery = true)
    public List<Student> getYoungestStudent();

}
