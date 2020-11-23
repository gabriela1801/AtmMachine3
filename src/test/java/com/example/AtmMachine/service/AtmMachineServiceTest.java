package com.example.AtmMachine.service;

import com.example.AtmMachine.config.ClientDetailsJpaEntity;
import com.example.AtmMachine.config.ClientDetailsRepository;
import com.example.AtmMachine.config.ContType;
import com.example.AtmMachine.config.TransactionDetailsDto;
import com.example.AtmMachine.exception.InsufficientResourcesException;
import com.example.AtmMachine.exception.InvalidOperationException;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AtmMachineServiceTest {

    private final TransactionDetailsDto transactionDetailsDtoRetrieve = new TransactionDetailsDto(
            "4567 1244 8975 7788",
            100,
            "RETRIEVE"
    );

    private final TransactionDetailsDto transactionDetailsDtoRetrieve2 = new TransactionDetailsDto(
            "4567 1244 8975 7788",
            7500,
            "RETRIEVE"
    );

    private final TransactionDetailsDto transactionDetailsDtoInsert = new TransactionDetailsDto(
            "4567 1244 8975 7788",
            200,
            "INSERT"
    );

    private final TransactionDetailsDto transactionDetailsDtoCheck = new TransactionDetailsDto(
            "4567 1244 8975 7788",
            "CHECK"
    );

    private final TransactionDetailsDto transactionDetailsDtoUnknown = new TransactionDetailsDto(
            "4567 1244 8975 7788",
            100,
            "UNKNOWN"
    );

    private ClientDetailsJpaEntity clientDetailsJpaEntity = new ClientDetailsJpaEntity(
            1,
            "Gabriela",
            "Sfetcu",
            7000,
            "4567 1244 8975 7788",
            ContType.LEI
    );

    @Mock
    private ClientDetailsRepository clientDetailsRepositoryMock;

    @InjectMocks
    private AtmMachineService testSubject;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(clientDetailsRepositoryMock.findAll()).thenReturn(List.of(clientDetailsJpaEntity));
        when(clientDetailsRepositoryMock.findByCardNumber(transactionDetailsDtoRetrieve.getCardNumber())).thenReturn(clientDetailsJpaEntity);
    }

    @Test
    public void retrieveAmount() throws InvalidOperationException, InsufficientResourcesException {
        Integer result = testSubject.handleOperation(transactionDetailsDtoRetrieve);
        Assert.assertEquals(transactionDetailsDtoRetrieve.getAmount().get(), result);
    }

    @Test(expected = InsufficientResourcesException.class)
    public void retrieveAmountShouldThrowError() throws InvalidOperationException, InsufficientResourcesException {
        testSubject.handleOperation(transactionDetailsDtoRetrieve2);
    }

    @Test
    public void insertAmount() throws InvalidOperationException, InsufficientResourcesException {
        when(clientDetailsRepositoryMock.findByCardNumber(transactionDetailsDtoRetrieve.getCardNumber())).thenReturn(clientDetailsJpaEntity);
        Integer result = testSubject.handleOperation(transactionDetailsDtoInsert);
        Integer expected = 7200;
        Assert.assertEquals(expected, result);
    }

    @Test
    public void checkAmount() throws InvalidOperationException, InsufficientResourcesException {
        Integer result = testSubject.handleOperation(transactionDetailsDtoCheck);
        Integer expected = 7000;
        Assert.assertEquals(expected, result);
    }

    @Test(expected = InvalidOperationException.class)
    public void shouldThrowError() throws InvalidOperationException, InsufficientResourcesException {
        testSubject.handleOperation(transactionDetailsDtoUnknown);
    }

}
