package com.example.budgettracker;

import com.example.budgettracker.exception.ResourcesNotFound;
import com.example.budgettracker.service.IncomeService;
import com.example.budgettracker.steps.IncomeTestSteps;
import com.example.budgettracker.transfer.income.CreateIncomeRequest;
import com.example.budgettracker.transfer.income.IncomeResponse;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class IncomeServiceIntegrationTests {

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private IncomeTestSteps incomeTestSteps;

    @Test
    public void createIncome_whenValidRequest_ThenReturnIncome(){
        incomeTestSteps.createIncome();
    }

    @Test
    public void getIncome_whenExistingIncome_thenReturnIncome(){
        IncomeResponse income = incomeTestSteps.createIncome();

        IncomeResponse response = incomeService.getIncomeResponse(income.getId());

        assertThat(response, notNullValue());
        assertThat(response.getId(), is(response.getId()));
        assertThat(response.getDescription(), is(response.getDescription()));
        assertThat(response.getDate(), is(response.getDate()));
        assertThat(response.getAmount(), is(response.getAmount()));
        assertThat(response.getCurrency(), is(response.getCurrency()));


    }

    @Test
    public void getIncome_whenNonExistingReview_thenThrowError(){
        Assertions.assertThrows(ResourcesNotFound.class, ()-> incomeService.getIncomeResponse(0));
    }

    @Test
    public void updateIncome_whenExistingIncome_thenReturnUpdatedIncome(){
        IncomeResponse income = incomeTestSteps.createIncome();

        CreateIncomeRequest request = new CreateIncomeRequest();
        request.setDescription("UpdatedIncomeTest");
        request.setDate("15-05-2020");
        request.setAmount(689.22);
        request.setCurrency("EUR");

        IncomeResponse updatedIncome = incomeService.updateIncome(income.getId(), request);

        assertThat(updatedIncome, CoreMatchers.notNullValue());
        assertThat(updatedIncome.getId(), is(income.getId()));
        assertThat(updatedIncome.getDescription(), is(request.getDescription()));
        assertThat(updatedIncome.getDate(), is(request.getDate()));
        assertThat(updatedIncome.getAmount(), is(request.getAmount()));
        assertThat(updatedIncome.getCurrency(), is(request.getCurrency()));
    }

    @Test
    public void updateIncome_whenIncomeDoesNotExist_thenThrowError(){
        CreateIncomeRequest request = new CreateIncomeRequest();
        request.setDescription("UpdatedIncomeTest");
        request.setDate("15-05-2020");
        request.setAmount(689.22);
        request.setCurrency("EUR");

        Assertions.assertThrows(ResourcesNotFound.class, ()->incomeService.updateIncome(0,request));

    }

    @Test
    public void deleteIncome_whenExistingIncome_thenReturnNothing(){
        IncomeResponse income = incomeTestSteps.createIncome();

        incomeService.deleteIncome(income.getId());

        Assertions.assertThrows(ResourcesNotFound.class, ()->incomeService.getIncome(income.getId()));
    }
}
