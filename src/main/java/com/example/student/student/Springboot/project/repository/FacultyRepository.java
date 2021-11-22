package com.example.student.student.Springboot.project.repository;

import com.example.student.student.Springboot.project.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
