package com.example.student.student.Springboot.project.controller;

import com.example.student.student.Springboot.project.entity.ResponseObject;
import com.example.student.student.Springboot.project.entity.Student;
import com.example.student.student.Springboot.project.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v2/students")
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @GetMapping("/allStudent")
    List<Student> getAllStudent(){
        List<Student> all = repository.findAll();
        List<Student> getExistOnly = all.stream().filter(student->student.isExist()).collect(Collectors.toList());
        return getExistOnly;
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findStudentById(@PathVariable Long id){
        Optional<Student> student = repository.findById(id);
        return student.isPresent() && student.get().isExist() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Find Student Successfully", student)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Can not find student", "")
                );
    }

    @PostMapping("/insertStudent")
    ResponseEntity<ResponseObject> insertStudent(@RequestBody Student student){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        List<Student> firstName = repository.findByFirstName(student.getFirstName());
        List<Student> lastName = repository.findByLastName(student.getLastName());
        List<Student> email = repository.findByEmail(student.getEmail());
        System.out.println("Kết quả: \n-" +  firstName + "\n-" + lastName + "\n-" + email + "\nasd");
        student.setExist(true);
        student.setCreateDate(formatter.format(date));
        student.setUpdateDate(formatter.format(date));
        System.out.println(getAllStudent());
        return /* firstName.size() != 0 && lastName.size() != 0 && */ email.size() != 0 ?
                ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                        .body(new ResponseObject("failed", "Can not insert duplicate student", ""))
                :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("ok", "Insert student successfully", repository.save(student)));
    }

    @PutMapping("/update/{id}")
    ResponseEntity<ResponseObject> updateStudent(@RequestBody Student newStudent, @PathVariable Long id){
        Student studentUpdate = repository.findById(id).map(student -> {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            student.setFirstName(newStudent.getFirstName());
            student.setLastName(newStudent.getLastName());
            student.setEmail(newStudent.getEmail());
            student.setUpdateDate(formatter.format(date));
            return repository.save(student);
        }).orElseGet(()->{
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            newStudent.setId(id);
            newStudent.setExist(true);
            newStudent.setCreateDate(formatter.format(date));
            newStudent.setUpdateDate(formatter.format(date));
            return repository.save(newStudent);
        });
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("ok", "Update student successfully", studentUpdate));
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseObject> deleteStudent(@PathVariable Long id){
        boolean existed = repository.existsById(id);
        if (existed){
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            Optional<Student> findStudent = repository.findById(id);
            Student student = findStudent.get();
            if (!student.isExist()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("failed", "Not exists", ""));
            }
            student.setExist(false);
            student.setUpdateDate(formatter.format(date));
            Student updateExistStatus = repository.save(student);
//            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("ok", "Delete student successfully", updateExistStatus));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseObject("failed", "Not exists", ""));
    }

    @PutMapping("/restore/{id}")
    ResponseEntity<ResponseObject> restoreStudent(@PathVariable Long id){
        boolean existed = repository.existsById(id);
        if (existed){
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            Optional<Student> student = repository.findById(id);
            Student studentRestore = student.get();
            if (studentRestore.isExist()){
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                        .body(new ResponseObject("failed", "This student was already existed", ""));
            }
            studentRestore.setExist(true);
            studentRestore.setUpdateDate(formatter.format(date));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("ok", "Restore student successfully", repository.save(studentRestore)));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseObject("failed", "Not exists", ""));
    }

    @DeleteMapping("/completelydelete/{id}")
    ResponseEntity<ResponseObject> completelyDelete(@PathVariable Long id){
        boolean exist = repository.existsById(id);
        if (exist){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("ok", "Delete Student Successfully permantly", ""));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("failed", "Not exists", ""));
        }
    }

}
