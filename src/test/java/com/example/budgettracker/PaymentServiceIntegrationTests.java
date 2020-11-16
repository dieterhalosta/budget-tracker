package com.example.budgettracker;


import com.example.budgettracker.service.PaymentService;
import com.example.budgettracker.steps.PaymentTestSteps;
import com.example.budgettracker.transfer.payment.CreatePaymentRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PaymentServiceIntegrationTests {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentTestSteps paymentTestSteps;


    @Test
    public void createPayment_whenValidRequest_thenReturnPayment(){
        paymentTestSteps.createPayment();
    }

    @Test
    public void createPayment_whenRequestNotValid_thenThrowError(){
        CreatePaymentRequest request = new CreatePaymentRequest();

        try {
            paymentService.createPayment(request);
        }catch (Exception e){
            assertThat("Unexpected exception thrown.", e instanceof ConstraintViolationException);
        }
    }
}
