package com.example.budgettracker;


import com.example.budgettracker.exception.ResourcesNotFound;
import com.example.budgettracker.service.PaymentService;
import com.example.budgettracker.steps.PaymentTestSteps;
import com.example.budgettracker.transfer.payment.CreatePaymentRequest;
import com.example.budgettracker.transfer.payment.PaymentResponse;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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

    @Test
    public void getPayment_whenValidRequest_thenReturnPayment(){
        PaymentResponse payment = paymentTestSteps.createPayment();

        PaymentResponse request = paymentService.getPaymentResponse(payment.getId());

        assertThat(request, notNullValue());
        assertThat(request.getId(), is(payment.getId()));
        assertThat(request.getDescription(), is(payment.getDescription()));
        assertThat(request.getDate(), is(payment.getDate()));
        assertThat(request.getAmount(), is(payment.getAmount()));
        assertThat(request.getCurrency(), is(payment.getCurrency()));
    }

    @Test
    public void getPayment_whenNonExistingPayment_thenThrowError(){
        Assertions.assertThrows(ResourcesNotFound.class, ()-> paymentService.getPaymentResponse(0));
    }

    @Test
    public void updatePayment_whenValidRequest_thenReturnUpdatedPayment(){
        PaymentResponse payment = paymentTestSteps.createPayment();

        CreatePaymentRequest request = new CreatePaymentRequest();
        request.setDescription("UpdatedPaymentTest");
        request.setDate("05-05-2020");
        request.setAmount(633.22);
        request.setCurrency("EUR");


        PaymentResponse updatedPayment = paymentService.updatePayment(payment.getId(), request);

        assertThat(updatedPayment, CoreMatchers.notNullValue());
        assertThat(updatedPayment.getId(), is(payment.getId()));
        assertThat(updatedPayment.getDescription(), is(request.getDescription()));
        assertThat(updatedPayment.getDate(), is(request.getDate()));
        assertThat(updatedPayment.getAmount(), is(request.getAmount()));
        assertThat(updatedPayment.getCurrency(), is(request.getCurrency()));
    }

    @Test
    public void updatePayment_whenPaymentDoesNotExist_thenThrowError(){
        CreatePaymentRequest request = new CreatePaymentRequest();
        request.setDescription("UpdatedPaymentTest");
        request.setDate("05-05-2020");
        request.setAmount(633.22);
        request.setCurrency("EUR");

        Assertions.assertThrows(ResourcesNotFound.class, ()-> paymentService.updatePayment(0, request));

    }


    @Test
    public void deletePayment_whenExistingPayment_thenReturnNothing(){
        PaymentResponse payment = paymentTestSteps.createPayment();

        paymentService.deletePayment(payment.getId());

        Assertions.assertThrows(ResourcesNotFound.class, ()->paymentService.getPayment(payment.getId()));
    }

}
