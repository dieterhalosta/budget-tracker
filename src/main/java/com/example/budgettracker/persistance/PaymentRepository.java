package com.example.budgettracker.persistance;

import com.example.budgettracker.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
