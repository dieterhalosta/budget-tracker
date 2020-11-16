package com.example.budgettracker.web;

import com.example.budgettracker.service.PaymentService;
import com.example.budgettracker.transfer.payment.CreatePaymentRequest;
import com.example.budgettracker.transfer.payment.PaymentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;


    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody @Valid CreatePaymentRequest request){
        PaymentResponse payment = paymentService.createPayment(request);

        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable long id){
        PaymentResponse payment = paymentService.getPaymentResponse(id);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponse> updatePayment(@PathVariable long id, @RequestBody @Valid CreatePaymentRequest request){
        PaymentResponse payment = paymentService.updatePayment(id, request);

        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentResponse> deletePayment (@PathVariable long id){
        paymentService.deletePayment(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
