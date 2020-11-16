package com.example.budgettracker.service;

import com.example.budgettracker.domain.Payment;
import com.example.budgettracker.exception.ResourcesNotFound;
import com.example.budgettracker.persistance.PaymentRepository;
import com.example.budgettracker.transfer.payment.CreatePaymentRequest;
import com.example.budgettracker.transfer.payment.PaymentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository){this.paymentRepository = paymentRepository;}

    public PaymentResponse createPayment(CreatePaymentRequest request){
        LOGGER.info("Creating payment {}", request);

        Payment payment = new Payment();
        payment.setAmount(request.getAmount());
        payment.setDescription(request.getDescription());
        payment.setCurrency(request.getCurrency());
        payment.setDate(request.getDate());

        Payment savedPayment = paymentRepository.save(payment);

        return mapPaymentResponse(savedPayment);
    }

    public Payment getPayment(long id){
        LOGGER.info("Getting payment {}", id);

       return paymentRepository.findById(id).orElseThrow(() -> new ResourcesNotFound("Payment " + id + " not found"));

    }

    public PaymentResponse getPaymentResponse(long id){
        Payment payment = getPayment(id);

        return mapPaymentResponse(payment);
    }

    public PaymentResponse updatePayment(long id, CreatePaymentRequest request){
        LOGGER.info("Updating payment {}", id);

        Payment payment = getPayment(id);
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setDate(request.getDate());
        payment.setDescription(request.getDescription());

        Payment updatedPayment = paymentRepository.save(payment);

        return mapPaymentResponse(updatedPayment);

    }


    private PaymentResponse mapPaymentResponse(Payment payment){
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setId(payment.getId());
        paymentResponse.setDate(payment.getDate());
        paymentResponse.setAmount(payment.getAmount());
        paymentResponse.setDescription(payment.getDescription());
        paymentResponse.setCurrency(payment.getCurrency());

        return paymentResponse;
    }

}
