package com.tsitohaina.springangular.web;

import com.tsitohaina.springangular.entities.Payement;
import com.tsitohaina.springangular.entities.PayementStatus;
import com.tsitohaina.springangular.entities.PayementType;
import com.tsitohaina.springangular.entities.Student;
import com.tsitohaina.springangular.repository.PayementRepository;
import com.tsitohaina.springangular.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PayementRestController {
    private final StudentRepository studentRepository;
    private final PayementRepository payementRepository;


    public PayementRestController(StudentRepository studentRepository, PayementRepository payementRepository) {
        this.studentRepository = studentRepository;
        this.payementRepository = payementRepository;
    }

    @GetMapping(path = "/payments")
    public List<Payement> allPayement(){
        return payementRepository.findAll();
    }

    @GetMapping(path = "/students/{code}/payements")
    public List<Payement> paymentByStudent(@PathVariable String code){
        return payementRepository.findByStudentCode(code);
    }

    @GetMapping(path = "/payements")
    public List<Payement> paymentByStatus(@RequestParam PayementStatus status){
        return payementRepository.findByStatus(status);
    }

    @GetMapping(path = "/payements/bytype")
    public List<Payement> paymentByType(@RequestParam PayementType type){
        return payementRepository.findByType(type);
    }

    @GetMapping(path = "/payment/{id}")
    public Payement getPayementById(@PathVariable Long id){
        return payementRepository.findById(id).get();
    }

    @GetMapping(path = "/students")
    public List<Student> allStudents(){
        return studentRepository.findAll();
    }

    @GetMapping(path = "/students/{code}")
    public Student getStudentByCode(@PathVariable String code){
        return studentRepository.findByCode(code);
    }

    @GetMapping(path = "/studentsByProramId")
    public List<Student> getStudentByProgramId(@RequestParam String programId) {
        return studentRepository.findByProgramId(programId);
    }
}
