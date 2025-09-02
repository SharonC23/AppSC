package com.miApp.AppS.service;

import com.miApp.AppS.dto.TransactionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {

    List <TransactionDTO> getAllTransactions();

    TransactionDTO findAllTransactions();
    TransactionDTO createTransaction(TransactionDTO transactionDTO);
    TransactionDTO getTransactionById(Long transactionId);
    TransactionDTO updateTransaction(Long transactionId, TransactionDTO transactionDTO);
    boolean deleteTransaction(Long transactionId);

}
