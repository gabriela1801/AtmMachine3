package com.example.AtmMachine.in;

import com.example.AtmMachine.config.Response;
import com.example.AtmMachine.config.TransactionDetailsDto;
import com.example.AtmMachine.exception.InsufficientResourcesException;
import com.example.AtmMachine.exception.InvalidOperationException;
import com.example.AtmMachine.service.AtmMachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/atm")
public class AtmMachineController {

    private static final Logger logger = LoggerFactory.getLogger(AtmMachineController.class);

    @Autowired
    private AtmMachineService atmMachineService;

    @RequestMapping(value = "/greetings", method = RequestMethod.GET)
    public String welcomeMessage() {
        return "Hello! Please specify the operation type:";
    }

    @RequestMapping(value = "/amount/retrieve", method = RequestMethod.POST)
    public Response retrieveAmount(
            @RequestBody TransactionDetailsDto transactionIdentifierDto
    ) throws InvalidOperationException {
        try {
            Integer amount = atmMachineService.handleOperation(transactionIdentifierDto);
            Response response = new Response(
                    Response.SUCCESS,
                    "The specified amount has been retrieved",
                    amount

            );
            logger.info("Response {}", response);
            return response;
        } catch (InsufficientResourcesException e) {
            logger.error("Error: {}", e.getMessage());
            return new Response(
               Response.FAILURE,
               e.getMessage()
            );
        }
    }

    @RequestMapping(value = "/amount/insert", method = RequestMethod.POST)
    public Response insertAmount(@RequestBody TransactionDetailsDto transactionIdentifierDto) throws InvalidOperationException {
        try {
            Integer currentBalance = atmMachineService.handleOperation(transactionIdentifierDto);
            Response response = new Response(
                    Response.SUCCESS,
                    "The specified amount has been inserted",
                    currentBalance
            );
            logger.info("Response {}", response);
            return response;
        } catch (InsufficientResourcesException e) {
            logger.error("Error: {}", e.getMessage());
            return new Response(Response.FAILURE, e.getMessage());
        }
    }

    @RequestMapping(value = "/amount/check", method = RequestMethod.POST)
    public Response checkAmount(@RequestBody TransactionDetailsDto transactionIdentifierDto) throws InvalidOperationException {
        try {
            Integer currentBalance = atmMachineService.handleOperation(transactionIdentifierDto);
            Response response = new Response(
                    Response.SUCCESS,
                    "Your current balance is the following:",
                    currentBalance
            );
            logger.info("Response {}", response);
            return response;
        } catch (InsufficientResourcesException e) {
            logger.error("Error: {}", e.getMessage());
            return new Response(Response.FAILURE, e.getMessage());
        }
    }

}




