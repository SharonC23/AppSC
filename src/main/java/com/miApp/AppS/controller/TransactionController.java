package com.miApp.AppS.controller;


import com.miApp.AppS.dto.TransactionDTO;
import com.miApp.AppS.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("api/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("api/{idTransaction}")
    public ResponseEntity<TransactionDTO> getTransactionById(Long transactionId) {
        TransactionDTO transaction = transactionService.getTransactionById(transactionId);
        return ResponseEntity.ok(transaction);
    }
    @PostMapping("api/create")
    public ResponseEntity<TransactionDTO> createTransaction(@Validated @RequestBody TransactionDTO transactionDTO) {
        transactionService.createTransaction(transactionDTO);
        return ResponseEntity.ok(transactionDTO);
    }

    @PutMapping ("api/update/{transactionId}")
    public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable Long transactionId, @RequestBody TransactionDTO transactionDTO) {
        TransactionDTO updatedTransaction = transactionService.updateTransaction(transactionId, transactionDTO);
        return ResponseEntity.ok(updatedTransaction);
    }

    @DeleteMapping ("api/delete/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long transactionId) {
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.noContent().build();
    }



}
