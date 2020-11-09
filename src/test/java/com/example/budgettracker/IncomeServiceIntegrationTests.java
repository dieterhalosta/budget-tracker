package com.example.budgettracker;

import com.example.budgettracker.service.IncomesService;
import com.example.budgettracker.steps.IncomeTestSteps;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class IncomeServiceIntegrationTests {

    @Autowired
    private IncomesService incomesService;

    @Autowired
    private IncomeTestSteps incomeTestSteps;

    @Test
    public void createIncome_whenValidRequest_ThenReturnIncome(){
        incomeTestSteps.createIncome();
    }
}
