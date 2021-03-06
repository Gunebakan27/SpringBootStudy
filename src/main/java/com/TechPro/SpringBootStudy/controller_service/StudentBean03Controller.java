package com.TechPro.SpringBootStudy.controller_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentBean03Controller {

    private StudentBean03Service std;

  @Autowired
    public StudentBean03Controller(StudentBean03Service std){
        this.std = std;
    }

   // @GetMapping(path="/getStudentById")
   // public StudentBean03 getStudentIdile(){
//
   //     return std.getStudentById(104L);
   // }

    // dinamik id ile bilgileri getir.
    @GetMapping(path="/getStudentById/{id}")
    public StudentBean03 getStudentId(@PathVariable Long id) {
        return std.getStudentById(id);
    }
}
