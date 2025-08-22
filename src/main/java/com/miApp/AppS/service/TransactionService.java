package com.miApp.AppS.service;

import com.miApp.AppS.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {

    List <TransactionDTO> getAllTransactions();

    TransactionDTO findAllTransactions();
    TransactionDTO createTransaction(TransactionDTO transactionDTO);
    TransactionDTO getTransactionById(Long transactionId);
    TransactionDTO updateTransaction(Long transactionId, TransactionDTO transactionDTO);
    boolean deleteTransaction(Long transactionId);

}
