package com.TechPro.SpringBootStudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @Controller //SpringBot  bu class'ı control layer olarak tanımlar
@RestController
public class StudentBean01Controller {

//    @RequestMapping(method= RequestMethod.GET, path = "/getRequest")    //sadece get işlemlerinde  ve bu adresten geldiyese bu metoduu çalıştır
//    @ResponseBody   //Method'un return ettiği datayı gösteriri--> tavsiye edilmez
//    public String getMetod1(){
//      return "Get Request method çalıştı";
//    }

    @GetMapping(path="/getRequest") // @responseBody yerine tavsiye edilen bu yöntemdir.
    public String getMetod2(){
     return "agam bu method daha başarılı";
       }

       // tight coupling... ornek onerilmez
    @GetMapping(path="/getObjectTight")
    public StudentBean01 getObjectTight(){
        return new StudentBean01("islam güzel",33,"ig202233");
       }


    // Loose coupling... ornek onerilmez
    @Autowired  //StudentBean01 data type'de IoC conteine'e default obj create eder.
    StudentBean01 s;
    @GetMapping(path="/getObjectLoose")
    public StudentBean01 getObjectLoose(){
        s.setName("Gökhan Harika");
        s.setAge(33);
        s.setId("gh202241");
        return s;
    }
    // tight coupling... Parametreli url--> url ile girilen parametreyi return edilen datada atanması
    @GetMapping(path="/getObjectParametreli/{school}")
    public StudentBean01 getObjectParametreli(@PathVariable String school){
        return new StudentBean01("cengiz erdem",44, String.format("ce1978%s",school));
    }

    // tight coupling... List return
    @GetMapping(path="/getObjectList")
    public List<StudentBean01> getObjectList(){
        return List.of(new StudentBean01("gohkan bey",33,"gb202233"),
        (new StudentBean01("islam bey",43,"ib202243")),
        (new StudentBean01("hatice hanım",27,"hh202227")));
    }

    @Autowired
    StudentBean01 t;
    @GetMapping(path="/getStudy")
    public String  getStudy01(){
        return t.study();
    }

    @Autowired
            @Qualifier("studentBean01") // @Qualifier--> @Autowired ile tanımlanan obj create edilecek data type ni tanımlar.
                       // Tanımlanacak data type annotayion a parametre olur.

    StudentInterface u;
    @GetMapping(path="/getStudy1")
    public String getStudy02(){
        return u.study();
    }
}
