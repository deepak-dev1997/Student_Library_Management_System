package com.example.Student_Library_Management.Controllers;

import com.example.Student_Library_Management.DTOs.StudentUpdateMobDto;
import com.example.Student_Library_Management.Models.Student;
import com.example.Student_Library_Management.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping("/get_user")
    public String getNameByEmail(@RequestParam("email")String mail){
        return studentService.findByEmail(mail);
    }

    @PutMapping("/update_mob")
    public String updateMob(@RequestBody StudentUpdateMobDto studentUpdateMobDto){
        return studentService.updateMobNo(studentUpdateMobDto);
    }

}
