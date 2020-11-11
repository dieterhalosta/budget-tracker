package com.example.budgettracker.steps;

import com.example.budgettracker.service.IncomeService;
import com.example.budgettracker.transfer.income.IncomeResponse;
import com.example.budgettracker.transfer.income.CreateIncomeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.in;


@Component
public class IncomeTestSteps {

    @Autowired
    private IncomeService incomesService;

    @Autowired
    private UserTestSteps userTestSteps;

    public IncomeResponse createIncome(){

        CreateIncomeRequest request = new CreateIncomeRequest();
        request.setDescription("TestIncome");
        request.setAmount(123.44);
        request.setCurrency("EUR");
        request.setDate("22.11.2020");


        IncomeResponse income = incomesService.createIncome(request);

        assertThat(income, notNullValue());
        assertThat(income.getId(), greaterThan(0L));
        assertThat(income.getAmount(), is(request.getAmount()));
        assertThat(income.getCurrency(), is(request.getCurrency()));
        assertThat(income.getDate(), is(request.getDate()));
        assertThat(income.getDescription(), is(request.getDescription()));

        return income;
    }
}
