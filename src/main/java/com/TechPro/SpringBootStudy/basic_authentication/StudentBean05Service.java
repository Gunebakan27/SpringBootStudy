package com.TechPro.SpringBootStudy.basic_authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class StudentBean05Service {
    private StudentBean05Repository studentRepo;//Repository layer'a ulaşmak için data type'nan obj create edildi.
    //obj degerini cons'dan alacak
    @Autowired
    public StudentBean05Service(StudentBean05Repository studentRepo){
        this.studentRepo = studentRepo;
    }
    //Bu method id ile ögrc return edecek
    public StudentBean05 selectStudentById(Long id){
        // return studentRepo.findById(id).get();--> olmayan id hata verir code kırlrı bununiçin kontrol if çalışmalı
        if (studentRepo.findById(id).isPresent()){
            return studentRepo.findById(id).get();
        }
        return new StudentBean05();//isteen id yoksa bos cons run edilecek
    }//service layer'de repository'den alınan datalar methodda çalıştırıldı. bu metthod controlle layer'da call edilmeli
public List<StudentBean05> selectAllStudents(){
        return studentRepo.findAll();
}
//Bu methoda var olan ogrencinin tüm datalarini (PUT: fully update) update eder
    public StudentBean05 updateFullyStudentById(Long id, StudentBean05 newStudent){
        StudentBean05 oldStd= studentRepo
                .findById(id).orElseThrow(()->new IllegalStateException(id+ " nolu ögrenci yok"));
if(newStudent.getName()==null){
    oldStd.setName(null);
//} else if(oldStd.getName()==null){
//oldStd.setName(newStudent.getName());
}else if(!oldStd.getName().equals(newStudent.getName())){
    oldStd.setName(newStudent.getName());
}
        //email aupdate edilecek
        /*
        brd:
        1) email tekararlı olmaz uniq-->EXCEPTION
        2) email gecerli (@ içermeli) olmalı-->EXCEPTION
        3) email null olamaz -->EXCEPTION
        4) email eski ve yeni aynı ise gereksiz işlem için update etmemeli
         */

Optional<StudentBean05> emailStd= studentRepo.findStudentBean05ByEmail(newStudent.getEmail());
        if(newStudent.getEmail()==null){
            newStudent.setEmail("");
        }
else if(emailStd.isPresent()){
    throw new IllegalStateException("daha önce bu email kullanildi");
}else if(!newStudent.getEmail().contains("@")&&newStudent.getEmail()!=""){
    throw new IllegalStateException("@ karakteri olmali");
}else if(newStudent.getEmail()==null){
    throw new IllegalStateException("mutlaka bir email girmelisiniz");
}else if(!newStudent.getEmail().equals(oldStd.getEmail())){
oldStd.setEmail(newStudent.getEmail());
}
// dob update edilecek
        /*
        1)girilen dob gelecekten olmamali hayatinb akisina ters oldugu icin excp
        2)girilen dob ayni olmamali gereksiz islem olur -->excp
         */
        if(Period.between(newStudent.getDob(), LocalDate.now()).isNegative()){
throw new IllegalStateException("hatali dob girdiniz");
        }
        else if(newStudent.getDob().equals(oldStd.getDob())){
            oldStd.setDob(newStudent.getDob());
        }

return studentRepo.save(oldStd);
    }
    public String deleteStudentById(Long id){
        if (!studentRepo.existsById(id)) {
            throw new IllegalStateException(id+" li ögrenci bulunamadi");
        }
        studentRepo.deleteById(id);
        return id+" li ögrenci silindi";
    }
    public StudentBean05 updatePatchById(StudentBean05 newStudent, Long id){
      StudentBean05 oldStd=  studentRepo.findById(id).orElseThrow(()->new IllegalStateException("id'si "+id+" olan ögr yok"));
        //email aupdate edilecek
        /*
        brd:
        1) email tekararlı olmaz uniq-->EXCEPTION
        2) email gecerli (@ içermeli) olmalı-->EXCEPTION
        3) email null olamaz -->EXCEPTION
        4) email eski ve yeni aynı ise gereksiz işlem için update etmemeli
         */

        Optional<StudentBean05> emailStd= studentRepo.findStudentBean05ByEmail(newStudent.getEmail());
        if(newStudent.getEmail()==null){
            newStudent.setEmail("");
        }
        else if(emailStd.isPresent()){
            throw new IllegalStateException("daha önce bu email kullanildi");
        }else if(!newStudent.getEmail().contains("@")&&newStudent.getEmail()!=""){
            throw new IllegalStateException("@ karakteri olmali");
        }else if(newStudent.getEmail()==null){
            throw new IllegalStateException("mutlaka bir email girmelisiniz");
        }else if(!newStudent.getEmail().equals(oldStd.getEmail())){
            oldStd.setEmail(newStudent.getEmail());
        }
   return studentRepo.save(oldStd);
    }
    public StudentBean05 addStudent(StudentBean05 newStudent) throws ClassNotFoundException, SQLException {
       Optional<StudentBean05> oldStdEmail= studentRepo.findStudentBean05ByEmail(newStudent.getEmail());
        if(oldStdEmail.isPresent()){
            throw new IllegalStateException("daha önce bu email kullanildi");
        }
        if(newStudent.getName()==null){
            throw new IllegalStateException("Ad girilmedi");
        }
        //DB'de var olan max id call edip +1 hali yeni id olarak assign edilmeli
        //DB'ye JDBC connection ..
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC","root","1234");
        Statement st = con.createStatement();
        // max id get icin SQL sorgusu
        String sqlQueryForMaxId="select max(id) from students";
        ResultSet rs = st.executeQuery(sqlQueryForMaxId);
        Long maxId=0L;
        while (rs.next()){// bir sonraki satira gider, önceki satiri return eder
            maxId=rs.getLong(1);
        }
        newStudent.setId(maxId+1);
        newStudent.setAge(newStudent.getAge());
        newStudent.setErrMsg("Ögrenci eklendi");
return studentRepo.save(newStudent);
    }
}
