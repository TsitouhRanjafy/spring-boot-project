package com.tsitohaina.springangular;

import com.tsitohaina.springangular.entities.Payement;
import com.tsitohaina.springangular.entities.PayementStatus;
import com.tsitohaina.springangular.entities.PayementType;
import com.tsitohaina.springangular.entities.Student;
import com.tsitohaina.springangular.repository.PayementRepository;
import com.tsitohaina.springangular.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class SpringAngularApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAngularApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            PayementRepository payementRepository
    ) {
        return args -> {
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("Mohamed").code("112233").programId("SDIA")
                    .build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("Imane").code("112244").programId("SDIA")
                    .build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("Yasmine").code("112255").programId("GLSI")
                    .build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("Najat").code("112266").programId("BDCC")
                    .build());

            PayementType[] payementTypes = PayementType.values();
            Random random = new Random();
            studentRepository.findAll().forEach((Student st) -> {
                for (int i = 0; i < 10; i++) {
                    int index = random.nextInt(payementTypes.length);
                    Payement payement = Payement.builder()
                            .amount(1000+(int)(Math.random()+20000))
                            .type(payementTypes[index])
                            .status(PayementStatus.CREATED)
                            .date(LocalDate.now())
                            .student(st)
                            .build();
                    payementRepository.save(payement);
                }
            });
        };
    }



}
