package com.example.student.student.Springboot.project.repository;

import com.example.student.student.Springboot.project.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findBySubjectCode(String subjectCode);
    List<Subject> findBySubjectName(String subjectName);
}
