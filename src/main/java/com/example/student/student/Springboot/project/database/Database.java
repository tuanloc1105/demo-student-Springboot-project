package com.example.student.student.Springboot.project.database;

import com.example.student.student.Springboot.project.entity.Student;
import com.example.student.student.Springboot.project.entity.Subject;
import com.example.student.student.Springboot.project.repository.StudentRepository;
import com.example.student.student.Springboot.project.repository.SubjectRepository;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Configuration
public class Database {

    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabse(StudentRepository studentRepository, SubjectRepository subjectRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//                Date date = new Date();
//                Student s1 = new Student("first 1", "name 1", "mail 1");
//                s1.setCreateDate(formatter.format(date));s1.setUpdateDate(formatter.format(date));
//                Student s2 = new Student("first 2", "name 2", "mail 2");
//                s2.setCreateDate(formatter.format(date));s2.setUpdateDate(formatter.format(date));
//                Student s3 = new Student("first 3", "name 3", "mail 3");
//                s3.setCreateDate(formatter.format(date));s3.setUpdateDate(formatter.format(date));
//                logger.info("insert: " + studentRepository.save(s1));
//                logger.info("insert: " + studentRepository.save(s2));
//                logger.info("insert: " + studentRepository.save(s3));
//                Subject s1 = new Subject("MH1", "Trí Tuệ Nhân Tạo", 4, 45, true, formatter.format(date), formatter.format(date));
//                Subject s2 = new Subject("MH2", "Nhập Môn Học Máy", 3, 30, true, formatter.format(date), formatter.format(date));
//                logger.info("insert: " + subjectRepository.save(s1));
//                logger.info("insert: " + subjectRepository.save(s2));
            }
        };
    }
}
