package com.miApp.AppS.controller;


import com.miApp.AppS.dto.SavingMovementDTO;
import com.miApp.AppS.service.SavingMovementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/savingMovements")
public class SavingMovementController {

    private final SavingMovementService savingMovementService;

    public SavingMovementController(SavingMovementService savingMovementService) {
        this.savingMovementService = savingMovementService;
    }

    @GetMapping ("/all")
    public ResponseEntity<List<SavingMovementDTO>> getAllSavingMovements() {
        List<SavingMovementDTO> savingMovements = savingMovementService.getAllSavingMovements();
        return ResponseEntity.ok(savingMovements);
    }

    @GetMapping ("/{savingMovementId}")
    public ResponseEntity<SavingMovementDTO> getSavingMovementById(Long savingMovementId) {
        SavingMovementDTO savingMovement = savingMovementService.getSavingMovementById(savingMovementId);
        return ResponseEntity.ok(savingMovement);
    }

    @PostMapping("/create")
    public ResponseEntity<SavingMovementDTO> createSavingMovement(@RequestBody SavingMovementDTO savingMovementDTO) {
        SavingMovementDTO createdSavingMovement = savingMovementService.createSavingMovement(savingMovementDTO);
        return ResponseEntity.ok(createdSavingMovement);
    }

}
