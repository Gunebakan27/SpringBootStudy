package com.TechPro.SpringBootStudy.controller_service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentBean03Service {

    List<StudentBean03> listOfStudent = List.of(

            new StudentBean03(101L,"zekeriya canbal", "zekeriyacanbal@gmail.com", LocalDate.of(1980,12,5)),
            new StudentBean03(102L,"mehmet Akgül", "mehmetakgul@gmail.com", LocalDate.of(1981,1,15)),
            new StudentBean03(103L,"yıldız parlak", "yildizparlak@gmail.com", LocalDate.of(1985,11,25)),
            new StudentBean03(104L,"seyma hanmefendi", "asd@gmail.com", LocalDate.of(1990,2,14))
    );

    // id ile obj getirmek
    public StudentBean03 getStudentById(Long id){

        if (listOfStudent.stream().filter(t->t.getId()==id).collect(Collectors.toList()).isEmpty()){
            return new StudentBean03();
        }

        return listOfStudent.stream().filter(t->t.getId()==id).findFirst().get();
    }


}
