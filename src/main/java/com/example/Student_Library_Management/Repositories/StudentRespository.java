package com.example.Student_Library_Management.Repositories;

import com.example.Student_Library_Management.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRespository extends JpaRepository<Student,Integer> {

    Student findByEmail(String email);

    List<Student> findByCountry(String country);


}
