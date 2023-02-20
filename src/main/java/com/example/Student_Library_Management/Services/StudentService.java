package com.example.Student_Library_Management.Services;

import com.example.Student_Library_Management.DTOs.StudentUpdateMobDto;
import com.example.Student_Library_Management.Enums.CardStatus;
import com.example.Student_Library_Management.Models.Card;
import com.example.Student_Library_Management.Models.Student;
import com.example.Student_Library_Management.Repositories.StudentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRespository studentRespository;

    public String addStudent(Student student){
        Card card =new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudent(student);
        student.setCard(card);

        studentRespository.save(student);

        return "Student and card added successfully";

    }

    public String findByEmail(String email){
        Student student=studentRespository.findByEmail(email);
        return student.getName();
    }

    public String updateMobNo(StudentUpdateMobDto studentUpdateMobDto){
        Student student=studentRespository.findById(studentUpdateMobDto.getId()).get();
        student.setMobNo(studentUpdateMobDto.getMobNo());

        studentRespository.save(student);
        return "Mobile no has been updated successfully";
    }
}
