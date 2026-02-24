package com.tsitohaina.springangular.web;

import com.tsitohaina.springangular.entities.Payement;
import com.tsitohaina.springangular.entities.PayementStatus;
import com.tsitohaina.springangular.entities.PayementType;
import com.tsitohaina.springangular.entities.Student;
import com.tsitohaina.springangular.repository.PayementRepository;
import com.tsitohaina.springangular.repository.StudentRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

    @PutMapping(path = "/payment/{id}")
    public Payement updatePaymentStatus(@RequestParam PayementStatus status, Long id) {
        Payement payment = payementRepository.findById(id).orElse(null);
        if (payment == null)
            throw new RuntimeException("Payment not found");
        payment.setStatus(status);
        return payementRepository.save(payment);
    }

    @PostMapping(path = "/payments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payement savePayment(@RequestParam MultipartFile file,
                                LocalDate date,
                                double amount,
                                PayementType type,
                                String studentCode) {
        Path folderPath = Paths.get(System.getProperty("user.home"), "enset-data","payments");
        if (!Files.exists(folderPath)) {
            try {
                Files.createDirectories(folderPath);
            } catch (Exception e){
                throw new RuntimeException("Failed to create a directory"+e.getMessage());
            }
        }
        String fileName = UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"), "enset-data", "payments", fileName+".pdf");
        try {
            Files.copy(file.getInputStream(), filePath);
            Student student = studentRepository.findByCode(studentCode);
            Payement payment = Payement.builder()
                    .date(date)
                    .amount(amount)
                    .type(type)
                    .status(PayementStatus.CREATED)
                    .file(filePath.toUri().toString())
                    .student(student)
                    .build();
            return payementRepository.save(payment);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
