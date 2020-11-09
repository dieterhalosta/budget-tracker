package com.example.budgettracker.web;

import com.example.budgettracker.service.IncomesService;
import com.example.budgettracker.transfer.income.IncomeResponse;
import com.example.budgettracker.transfer.income.SaveIncomeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/incomes")
public class IncomeController {

    private final IncomesService incomesService;

    @Autowired
    public IncomeController(IncomesService incomesService) {
        this.incomesService = incomesService;
    }

    @PostMapping
    public ResponseEntity<IncomeResponse> createIncome (@Valid @RequestBody SaveIncomeRequest request){
        IncomeResponse income = incomesService.createIncome(request);

        return new ResponseEntity<>(income, HttpStatus.OK);
    }
}
