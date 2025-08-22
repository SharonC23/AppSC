package com.miApp.AppS.repository;


import com.miApp.AppS.entity.SavingMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavingMovementRepository extends JpaRepository<SavingMovement, Long>{

    SavingMovement findByIdSaving(Long idSaving);

}
