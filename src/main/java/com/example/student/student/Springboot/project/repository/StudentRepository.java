package com.example.student.student.Springboot.project.repository;

import com.example.student.student.Springboot.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFirstName(String firstName);
    List<Student> findByLastName(String lastName);
    List<Student> findByEmail(String email);
}
