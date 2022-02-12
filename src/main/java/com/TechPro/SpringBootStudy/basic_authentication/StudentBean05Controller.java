package com.TechPro.SpringBootStudy.basic_authentication;


import com.TechPro.SpringBootStudy.controller_service.StudentBean03;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class StudentBean05Controller {
    private StudentBean05Service stdSrvc;//service layer' ulaşmak için obj create edildi
    public StudentBean05Controller(StudentBean05Service stdSrvc){
        this.stdSrvc = stdSrvc;
    }
    //bu method id ile ogrc returnn eden service methodu call edecek
    @GetMapping(path = "/selectStudentById/{id}")
    public StudentBean05 selectStudentById(@PathVariable Long id){
        return stdSrvc.selectStudentById(id);
    }

    @GetMapping(path="selectAllStudents")
    public List<StudentBean05> getAllStudents() {
        return stdSrvc.selectAllStudents();
    }

    @PutMapping(path="/updateFullyById/{id}")
    public StudentBean05 updateById(@PathVariable Long id,@RequestBody StudentBean05 newStudent){

        return stdSrvc.updateFullyStudentById(id, newStudent);
    }
    @DeleteMapping(path="/deleteById/{id}")
    public String deleteStdById(@PathVariable Long id){
        return stdSrvc.deleteStudentById(id);
    }
    @PatchMapping(path="/updatePartialById/{id}")
    public StudentBean05 updatePartialById(@RequestBody StudentBean05 newStudent, @PathVariable Long id){
        return stdSrvc.updatePatchById(newStudent,id);
    }
@PostMapping(path="/addStudent")
    public StudentBean05 addStd(@RequestBody StudentBean05 newStudent) throws SQLException, ClassNotFoundException {
      return  stdSrvc.addStudent(newStudent);
}
}