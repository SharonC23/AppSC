package com.miApp.AppS.Impl;

import com.miApp.AppS.dto.TransactionDTO;
import com.miApp.AppS.entity.Category;
import com.miApp.AppS.entity.Transaction;
import com.miApp.AppS.entity.User;
import com.miApp.AppS.exceptions.CustomException;
import com.miApp.AppS.repository.CategoryRepository;
import com.miApp.AppS.repository.TransactionRepository;
import com.miApp.AppS.repository.UserRepository;
import com.miApp.AppS.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl  implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, ModelMapper modelMapper, UserRepository userRepository, CategoryRepository categoryRepository, UserRepository userRepository1, CategoryRepository categoryRepository1) {
        this.transactionRepository= transactionRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository1;
        this.categoryRepository = categoryRepository1;
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public TransactionDTO findAllTransactions() {
        return null;
    }

    @Override
    public TransactionDTO getTransactionById(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new CustomException("Transaction not found with id: " + transactionId));
        return modelMapper.map(transaction, TransactionDTO.class);
    }

    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {

        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        if (transactionDTO.getUserId() !=null) {
            User user = userRepository.findById(transactionDTO.getUserId()).orElseThrow(() -> new CustomException("User not found with id: " + transactionDTO.getUserId()));
            transaction.setUser(user);

        }
        if(transactionDTO.getCategoryId() !=null){
            Category category =categoryRepository.findById(Long.valueOf(transactionDTO.getCategoryId()))
                    .orElseThrow(() -> new CustomException("Category not found with id:" + transactionDTO.getCategoryId()));
            transaction.setCategory(category);
        }
        transaction =transactionRepository.save(transaction);
        return modelMapper.map(transaction, TransactionDTO.class);

    }

    @Override
    public TransactionDTO updateTransaction(Long transactionId, TransactionDTO transactionDTO) {
        if(transactionRepository.existsById(transactionId)) {
            Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
            transaction.setIdTransaction(transactionId);

            if (transactionDTO.getUserId() != null) {
                User user = userRepository.findById(transactionDTO.getUserId())
                        .orElseThrow(() -> new CustomException("User not found with id: " + transactionDTO.getUserId()));
                transaction.setUser(user);
            }

            if(transactionDTO.getCategoryId() !=null){
                Category category = categoryRepository.findById(Long.valueOf(transactionDTO.getCategoryId()))
                        .orElseThrow(()-> new CustomException("Category not found with id: " + transactionDTO.getCategoryId()));
                transaction.setCategory(category);
            }

            transaction = transactionRepository.save(transaction);
            return modelMapper.map(transaction, TransactionDTO.class);
        } else{
            throw new CustomException("Transaction not found with id: " + transactionId);
        }
    }

    @Override
    public boolean deleteTransaction(Long transactionId) {
        if (!transactionRepository.existsById(transactionId)) {
            throw new CustomException("Transaction not found with id: " + transactionId);
        }
        transactionRepository.deleteById(transactionId);
        return true;

    }
}

