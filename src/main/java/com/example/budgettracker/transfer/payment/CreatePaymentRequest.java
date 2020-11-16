package com.example.budgettracker.transfer.payment;

import javax.validation.constraints.NotNull;

public class CreatePaymentRequest {

    @NotNull
    private String description;
    @NotNull
    private String date;
    @NotNull
    private double amount;
    @NotNull
    private String currency;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
