package com.example.budgettracker.service;

import com.example.budgettracker.domain.Income;
import com.example.budgettracker.persistance.IncomeRepository;
import com.example.budgettracker.transfer.income.IncomeResponse;
import com.example.budgettracker.transfer.income.CreateIncomeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final IncomeRepository incomeRepository;

    @Autowired
    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }


    public IncomeResponse createIncome(CreateIncomeRequest request){
        LOGGER.info("Creating income {}", request);

        Income income = new Income();
        income.setAmount(request.getAmount());
        income.setCurrency(request.getCurrency());
        income.setDate(request.getDate());
        income.setDescription(request.getDescription());

        Income savedIncome =incomeRepository.save(income);

        return mapIncomeResponse(savedIncome);
    }

    private IncomeResponse mapIncomeResponse(Income income){
        IncomeResponse incomeResponse = new IncomeResponse();
        incomeResponse.setId(income.getId());
        incomeResponse.setDescription(income.getDescription());
        incomeResponse.setCurrency(income.getCurrency());
        incomeResponse.setAmount(income.getAmount());
        incomeResponse.setDate(income.getDate());

        return incomeResponse;
    }


}
