package com.example.budgettracker.service;

import com.example.budgettracker.persistance.IncomeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomesService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final IncomeRepository incomeRepository;

    @Autowired
    public IncomesService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }
}
