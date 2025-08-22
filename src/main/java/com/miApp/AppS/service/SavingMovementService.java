package com.miApp.AppS.service;

import com.miApp.AppS.dto.SavingMovementDTO;

import java.util.List;

public interface SavingMovementService {

    List <SavingMovementDTO> getAllSavingMovements();

    SavingMovementDTO findAllSavingMovements();

    SavingMovementDTO createSavingMovement(SavingMovementDTO savingMovementDTO);
    SavingMovementDTO getSavingMovementById(Long savingMovementId);
    SavingMovementDTO updateSavingMovement(Long savingMovementId, SavingMovementDTO savingMovementDTO);
    void deleteSavingMovement(Long savingMovementId);





}
