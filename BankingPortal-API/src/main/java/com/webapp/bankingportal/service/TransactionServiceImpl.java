package com.webapp.bankingportal.service;

import java.util.List;
import java.util.stream.Collectors;

import com.webapp.bankingportal.repository.AccountRepository;
import org.springframework.stereotype.Service;

import com.webapp.bankingportal.dto.TransactionDTO;
import com.webapp.bankingportal.mapper.TransactionMapper;
import com.webapp.bankingportal.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final EmailService emailService;
    private final AccountRepository accountRepository;

    @Override
    public List<TransactionDTO> getAllTransactionsByAccountNumber(String accountNumber) {
        var transactions = transactionRepository
                .findBySourceAccount_AccountNumberOrTargetAccount_AccountNumber(accountNumber, accountNumber);

        return transactions.parallelStream()
                .map(transactionMapper::toDto)
                .sorted((t1, t2) -> t2.getTransactionDate().compareTo(t1.getTransactionDate()))
                .collect(Collectors.toList());
    }

    @Override
    public void sendBankStatementByEmail(String accountNumber) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number must not be null or empty");
        }

        log.info("Generating bank statement for account: {}", accountNumber);

        List<TransactionDTO> transactions = getAllTransactionsByAccountNumber(accountNumber);

        StringBuilder sb = new StringBuilder();
        sb.append("Bank Statement for Account: ").append(accountNumber).append("\n\n");

        for (TransactionDTO txn : transactions) {
            sb.append("Date: ").append(txn.getTransactionDate())
                    .append(", Type: ").append(txn.getTransactionType())
                    .append(", Amount: ").append(txn.getAmount())
                    .append("\n");
        }

        var account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null || account.getUser() == null) {
            log.warn("Account or User not found for account number: {}", accountNumber);
            return;
        }
        String email = account.getUser().getEmail();
        emailService.sendEmail(email, "Your Bank Statement", sb.toString());
        log.info("Bank statement sent to email: {}", email);
    }

}
