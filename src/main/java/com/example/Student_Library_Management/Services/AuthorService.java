package com.example.Student_Library_Management.Services;

import com.example.Student_Library_Management.DTOs.AuthorEntityDto;
import com.example.Student_Library_Management.DTOs.AuthorResponseDto;
import com.example.Student_Library_Management.DTOs.BookResponseDto;
import com.example.Student_Library_Management.Models.Author;
import com.example.Student_Library_Management.Models.Book;
import com.example.Student_Library_Management.Repositories.AuthorRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRespository authorRespository;
    public String addAuthor(AuthorEntityDto authorEntityDto){

        Author author=new Author();
        author.setName(authorEntityDto.getName());
        author.setAge(authorEntityDto.getAge());
        author.setCountry(authorEntityDto.getCountry());
        author.setRating(authorEntityDto.getRating());

        authorRespository.save(author);
        return "Author added Successfully";

    }

    public AuthorResponseDto getAuthor(Integer authorId){

        Author author=authorRespository.findById(authorId).get();
        AuthorResponseDto authorResponseDto=new AuthorResponseDto();
        List<Book> bookList=author.getBooksWritten();

        List<BookResponseDto> booksWrittenDto=new ArrayList<>();

        for (Book b : bookList){
            BookResponseDto bookResponseDto=new BookResponseDto();
            bookResponseDto.setGenre(b.getGenre());
            bookResponseDto.setPages(b.getPages());
            bookResponseDto.setName(b.getName());

            booksWrittenDto.add(bookResponseDto);

        }

        authorResponseDto.setBooksWritten(booksWrittenDto);
        authorResponseDto.setName(author.getName());
        authorResponseDto.setAge(author.getAge());
        authorResponseDto.setRating(author.getRating());

        return authorResponseDto;


    }
}
