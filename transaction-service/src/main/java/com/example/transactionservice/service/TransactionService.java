package com.example.transactionservice.service;

import com.example.transactionservice.dto.AccountDTO;
import com.example.transactionservice.dto.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final RestTemplate restTemplate;

    @Value("${account.service.url}")
    private String accountServiceUrl;


    public String processTransfer(TransactionRequest request) {
        System.out.println("Processing transfer request: " + request + " with accountServiceUrl: " + accountServiceUrl);
        String url = accountServiceUrl + "/api/v1/accounts/" + request.getSourceAccountId();

        try {
            ResponseEntity<AccountDTO> response = restTemplate.getForEntity(url, AccountDTO.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                BigDecimal currentBalance = response.getBody().getBalance();
                return "Hợp lệ! Tài khoản nguồn tồn tại. Số dư hiện tại: " + currentBalance
                        + ". Sẵn sàng chuyển " + request.getAmount();

            }
        } catch (HttpClientErrorException.NotFound e) {
            return "Giao dịch thất bại: Tài khoản nguồn (ID: " + request.getSourceAccountId() + ") không tồn tại!";
        } catch (Exception e) {
            return "Lỗi hệ thống khi giao tiếp với Account Service: " + e.getMessage();
        }

        return "Giao dịch bị từ chối.";
    }
}
