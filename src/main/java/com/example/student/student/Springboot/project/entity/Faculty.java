package com.example.student.student.Springboot.project.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String faciltyCode;
    private String faciltyName;
    private String createDate;
    private String updateDate;
    private boolean exist;

//    @OneToMany(mappedBy = "faculty")
//    private Set<Student> student;

    public Faculty(String faciltyCode, String faciltyName) {
        this.faciltyCode = faciltyCode;
        this.faciltyName = faciltyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFaciltyCode() {
        return faciltyCode;
    }

    public void setFaciltyCode(String faciltyCode) {
        this.faciltyCode = faciltyCode;
    }

    public String getFaciltyName() {
        return faciltyName;
    }

    public void setFaciltyName(String faciltyName) {
        this.faciltyName = faciltyName;
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

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }
}
