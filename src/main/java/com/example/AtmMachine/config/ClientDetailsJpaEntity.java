package com.example.AtmMachine.config;

import com.example.AtmMachine.exception.InsufficientResourcesException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClientDetailsJpaEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer clientId;
    private String firstName;
    private String lastName;
    private Integer existingDebit;
    private final String cardNumber;
    private String contType;

    public ClientDetailsJpaEntity(Integer clientId, String firstName, String lastName, Integer existingDebit, String cardNumber, String contType) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.existingDebit = existingDebit;
        this.cardNumber = cardNumber;
        this.contType = contType;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s', existingDebit='%s', cardNumber='%s', contType='%s' ]",
                clientId, firstName, lastName, existingDebit, cardNumber, contType);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getExistingDebit() {
        return existingDebit;
    }

    public void setExistingDebit(Integer existingDebit) {
        this.existingDebit = existingDebit;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getContType() {
        return contType;
    }

    public void setContType(String contType) {
        this.contType = contType;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public void insertAmount(Integer amount) {
        this.existingDebit += amount;
    }

    public void retrieveAmount(Integer amount) throws InsufficientResourcesException {
        if (amount < this.existingDebit) {
            this.existingDebit -= amount;
        } else {
            throw new InsufficientResourcesException("Not enough resources");
        }
    }

}
