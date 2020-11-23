package com.example.AtmMachine.service;

import com.example.AtmMachine.config.ClientDetailsJpaEntity;
import com.example.AtmMachine.config.ClientDetailsRepository;
import com.example.AtmMachine.config.TransactionDetailsDto;
import com.example.AtmMachine.exception.InsufficientResourcesException;
import com.example.AtmMachine.exception.InvalidOperationException;
import com.example.AtmMachine.in.AtmMachineController;
import com.sun.istack.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtmMachineService {

    private static final Logger logger = LoggerFactory.getLogger(AtmMachineController.class);

    @Autowired
    private ClientDetailsRepository clientDetailsRepository;

    @NotNull
    public Integer handleOperation(TransactionDetailsDto transactionDetailsDto) throws InvalidOperationException, InsufficientResourcesException {
        return switch (transactionDetailsDto.getOperationType()) {
            case "CHECK" -> checkAmount(transactionDetailsDto);
            case "INSERT" -> insertAmount(transactionDetailsDto);
            case "RETRIEVE" -> retrieveAmount(transactionDetailsDto);
            default -> throw new InvalidOperationException("Invalid Operation");
        };
    }

    private Integer insertAmount(TransactionDetailsDto transactionDetailsDto) {
        logger.info("TransactionDetails {}", transactionDetailsDto);
        ClientDetailsJpaEntity client = clientDetailsRepository.findByCardNumber(transactionDetailsDto.getCardNumber());
        if (transactionDetailsDto.getAmount().isPresent()) {
            client.insertAmount(transactionDetailsDto.getAmount().get());
        }
        return client.getExistingDebit();
    }

    private Integer retrieveAmount(TransactionDetailsDto transactionDetailsDto) throws InsufficientResourcesException {
        logger.info("TransactionDetails {}", transactionDetailsDto);
        ClientDetailsJpaEntity client = clientDetailsRepository.findByCardNumber(transactionDetailsDto.getCardNumber());
        if (transactionDetailsDto.getAmount().isPresent()) {
            client.retrieveAmount(transactionDetailsDto.getAmount().get());
        }
        return transactionDetailsDto.getAmount().get();
    }

    private Integer checkAmount(TransactionDetailsDto transactionDetailsDto) {
        logger.info("TransactionDetails {}", transactionDetailsDto);
        ClientDetailsJpaEntity client = clientDetailsRepository.findByCardNumber(transactionDetailsDto.getCardNumber());
        return client.getExistingDebit();
    }
}
