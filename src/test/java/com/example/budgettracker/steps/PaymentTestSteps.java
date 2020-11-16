package com.example.budgettracker.steps;


import com.example.budgettracker.service.PaymentService;
import com.example.budgettracker.transfer.payment.CreatePaymentRequest;
import com.example.budgettracker.transfer.payment.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;

@Component
public class PaymentTestSteps {

    @Autowired
    private PaymentService paymentService;

    public PaymentResponse createPayment(){

        CreatePaymentRequest request = new CreatePaymentRequest();
        request.setAmount(44.445);
        request.setCurrency("EUR");
        request.setDate("22.10.2020");
        request.setDescription("TestPayment");

        PaymentResponse payment = paymentService.createPayment(request);

        assertThat(payment, notNullValue());
        assertThat(payment.getAmount(), is(request.getAmount()));
        assertThat(payment.getCurrency(), is(request.getCurrency()));
        assertThat(payment.getDate(), is(request.getDate()));
        assertThat(payment.getDescription(), is(request.getDescription()));

        return payment;
    }
}
