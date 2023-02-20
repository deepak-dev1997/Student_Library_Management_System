package com.example.Student_Library_Management.Controllers;

import com.example.Student_Library_Management.DTOs.AuthorEntityDto;
import com.example.Student_Library_Management.DTOs.AuthorResponseDto;
import com.example.Student_Library_Management.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;


    @PostMapping("/add")
    public String addAuthor(@RequestBody AuthorEntityDto authorEntityDto){
        return authorService.addAuthor(authorEntityDto);
    }

    @GetMapping("getAuthor")
        public AuthorResponseDto getAuthor(@RequestParam("authorId")Integer authorId){
        return authorService.getAuthor(authorId);
        }





}
