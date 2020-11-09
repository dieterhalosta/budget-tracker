package com.example.budgettracker.service;

import com.example.budgettracker.domain.Incomes;
import com.example.budgettracker.persistance.IncomeRepository;
import com.example.budgettracker.transfer.income.IncomeResponse;
import com.example.budgettracker.transfer.income.SaveIncomeRequest;
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


    public IncomeResponse createIncome(SaveIncomeRequest request){
        LOGGER.info("Creating income {}", request);

        Incomes incomes =new Incomes();
        incomes.setAmount(request.getAmount());
        incomes.setCurrency(request.getCurrency());
        incomes.setDate(request.getDate());
        incomes.setDescription(request.getDescription());
        incomes.setUser(request.getUser());

        Incomes saveIncome =incomeRepository.save(incomes);

        return mapIncomeResponse(saveIncome);
    }

    private IncomeResponse mapIncomeResponse(Incomes income){
        IncomeResponse incomeResponse = new IncomeResponse();
        incomeResponse.setId(income.getId());
        incomeResponse.setDescription(income.getDescription());
        incomeResponse.setCurrency(income.getCurrency());
        incomeResponse.setAmount(income.getAmount());
        incomeResponse.setDate(income.getDate());
        incomeResponse.setUser(income.getUser());

        return incomeResponse;
    }


}
