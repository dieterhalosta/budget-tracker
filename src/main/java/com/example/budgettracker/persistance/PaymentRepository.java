package com.example.budgettracker.persistance;

import com.example.budgettracker.domain.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payments, Long> {
}
