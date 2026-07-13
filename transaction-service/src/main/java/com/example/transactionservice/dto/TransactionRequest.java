package com.example.transactionservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequest {
    private Long sourceAccountId;
    private Long destinationAccountId;
    private BigDecimal amount;
}
