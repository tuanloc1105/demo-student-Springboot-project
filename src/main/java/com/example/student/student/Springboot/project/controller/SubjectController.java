package com.example.student.student.Springboot.project.controller;

import com.example.student.student.Springboot.project.entity.ResponseObject;
import com.example.student.student.Springboot.project.entity.Student;
import com.example.student.student.Springboot.project.entity.Subject;
import com.example.student.student.Springboot.project.repository.StudentRepository;
import com.example.student.student.Springboot.project.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v2/subject")
public class SubjectController {

    @Autowired
    private SubjectRepository repository;

    @GetMapping("/allsubject")
    List<Subject> getAllSubject(){
        List<Subject> all = repository.findAll();
        List<Subject> getExistOnly = all.stream().filter(subject->subject.isExist()).collect(Collectors.toList());
        return getExistOnly;
    }

    @GetMapping("/{mamh}")
    ResponseEntity<ResponseObject> findSubjectByCode(@PathVariable String mamh){
        Optional<Subject> subject = repository.findBySubjectCode(mamh);
        return subject.isPresent() && subject.get().isExist() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Find Subject Successfully", subject)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Can not find subject", "")
                );
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertSubject(@RequestBody Subject subject) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        List<Subject> subjects = repository.findBySubjectName(subject.getSubjectName());
        subject.setCodeById(repository.findAll().size() + 1);
        subject.setExist(true);
        subject.setCreateDate(formatter.format(date));
        subject.setUpdateDate(formatter.format(date));
        return subjects.size() != 0 ?
                ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                        .body(new ResponseObject("failed", "Can not insert duplicate subject", ""))
                :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("ok", "Insert subject successfully", repository.save(subject)));
    }

    @PutMapping("/update/{id}")
    ResponseEntity<ResponseObject> updateSubject(@RequestBody Subject subject, @PathVariable Long id){
        Subject updateSubject = repository.findById(id).map(sbj -> {
            sbj.setSubjectName(subject.getSubjectName());
            sbj.setCredit(subject.getCredit());
            sbj.setDuration(subject.getDuration());
            return repository.save(sbj);
        }).orElseGet(() -> {
            subject.setId(id);
            subject.setCodeById(repository.findAll().size() + 1);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            subject.setCreateDate(formatter.format(date));
            subject.setUpdateDate(formatter.format(date));
            return repository.save(subject);
        });
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("ok", "Update student successfully", updateSubject));
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseObject> deleteSubject(@PathVariable Long id){
        boolean existed = repository.existsById(id);
        if (existed){
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            Optional<Subject> findSubject = repository.findById(id);
            Subject subject = findSubject.get();
            if (!subject.isExist()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("failed", "Not exists", ""));
            }
            subject.setExist(false);
            subject.setUpdateDate(formatter.format(date));
            Subject updateExistStatus = repository.save(subject);
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
            Optional<Subject> subject = repository.findById(id);
            Subject subjectRestore = subject.get();
            if (subjectRestore.isExist()){
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                        .body(new ResponseObject("failed", "This student was already existed", ""));
            }
            subjectRestore.setExist(true);
            subjectRestore.setUpdateDate(formatter.format(date));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("ok", "Restore student successfully", repository.save(subjectRestore)));
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



//    @DeleteMapping
//    ResponseEntity<ResponseObject> deleteSubject()

}
