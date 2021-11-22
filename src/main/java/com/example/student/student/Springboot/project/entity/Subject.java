package com.example.student.student.Springboot.project.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subjectCode;
    private String subjectName;
    private int credit;
    private int duration;
    private boolean exist;
    private String createDate;
    private String updateDate;

//    @OneToMany(mappedBy = "subject")
//    private Set<SubjectRegistration> learnBy;

    public Subject(){}

    public Subject(String subjectName, int credit, int duration) {
        this.subjectName = subjectName;
        this.credit = credit;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setCodeById(int id){
        this.subjectCode = "MH" + String.valueOf(id);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", subjectCode='" + subjectCode + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", credit=" + credit +
                ", duration=" + duration +
                ", exist=" + exist +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }
}
