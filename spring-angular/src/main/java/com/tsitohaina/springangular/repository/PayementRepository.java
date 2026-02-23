package com.tsitohaina.springangular.repository;

import com.tsitohaina.springangular.entities.Payement;
import com.tsitohaina.springangular.entities.PayementStatus;
import com.tsitohaina.springangular.entities.PayementType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayementRepository extends JpaRepository<Payement, Long> {
    List<Payement> findByStudentCode(String code);
    List<Payement> findByStatus(PayementStatus status);
    List<Payement> findByType(PayementType type);
}
