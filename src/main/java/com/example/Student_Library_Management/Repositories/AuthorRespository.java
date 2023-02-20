package com.example.Student_Library_Management.Repositories;

import com.example.Student_Library_Management.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRespository extends JpaRepository<Author,Integer> {
}
