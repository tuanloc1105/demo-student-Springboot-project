package com.example.student.student.Springboot.project.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class SubjectRegistration implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
    @Id
    @ManyToOne
//    @JoinColumn(name = "student_id")
    private Student student;

    @Id
    @ManyToOne
//    @JoinColumn(name = "subject_id")
    private Subject subject;

    private String createTime;
    private String updateTime;
    private int grade;

    public SubjectRegistration(Student student, Subject subject) {
        this.student = student;
        this.subject = subject;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
