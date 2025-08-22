package com.miApp.AppS.repository;

import com.miApp.AppS.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    Transaction findByidTransaction(Long idTransaction);

}
