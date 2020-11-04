package com.example.budgettracker.persistance;

import com.example.budgettracker.domain.Incomes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Incomes, Long> {
}
