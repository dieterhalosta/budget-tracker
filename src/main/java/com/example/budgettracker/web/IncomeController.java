package com.example.budgettracker.web;

import com.example.budgettracker.domain.Income;
import com.example.budgettracker.service.IncomeService;
import com.example.budgettracker.transfer.income.IncomeResponse;
import com.example.budgettracker.transfer.income.CreateIncomeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/incomes")
public class IncomeController {

    private final IncomeService incomesService;

    @Autowired
    public IncomeController(IncomeService incomesService) {
        this.incomesService = incomesService;
    }

    @PostMapping
    public ResponseEntity<IncomeResponse> createIncome (@Valid @RequestBody CreateIncomeRequest request){
        IncomeResponse income = incomesService.createIncome(request);

        return new ResponseEntity<>(income, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncomeResponse> getIncome(@PathVariable long id){
        IncomeResponse income = incomesService.getIncomeResponse(id);
        return new ResponseEntity<>(income, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncomeResponse> updateIncome (@PathVariable long id,@Valid @RequestBody CreateIncomeRequest request){
           IncomeResponse income = incomesService.updateIncome(id, request);
           return new ResponseEntity<>(income, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<IncomeResponse> deleteIncome (@PathVariable long id){
        incomesService.deleteIncome(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
