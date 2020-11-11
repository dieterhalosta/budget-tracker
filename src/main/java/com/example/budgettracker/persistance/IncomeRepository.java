package com.example.budgettracker.persistance;

import com.example.budgettracker.domain.Income;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {

Page<Income> findByUserId(long userId, Pageable pageable);

}
