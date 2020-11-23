package com.example.AtmMachine.config;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransactionDetailsDto {
    private String cardNumber;
    private Integer amount;
    private String operationType;

    public TransactionDetailsDto(
            String cardNumber,
            Integer amount,
            String operationType
    ) {
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.operationType = operationType;
    }

    public TransactionDetailsDto(
            String cardNumber,
            String operationType
    ) {
        this.cardNumber = cardNumber;
        this.operationType = operationType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Optional<Integer> getAmount() {
        return Optional.ofNullable(amount);
    }

    public void setAmount(Integer wantedAmount) {
        this.amount = wantedAmount;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
}

