package com.example.Student_Library_Management.Repositories;

import com.example.Student_Library_Management.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepostory extends JpaRepository<Book,Integer> {

}
