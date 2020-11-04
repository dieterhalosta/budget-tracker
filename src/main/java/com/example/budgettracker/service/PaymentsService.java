package com.example.budgettracker.service;

import com.example.budgettracker.persistance.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentsService(PaymentRepository paymentRepository){this.paymentRepository = paymentRepository;}
}
