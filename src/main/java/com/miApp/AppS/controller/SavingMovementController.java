package com.miApp.AppS.controller;

import com.miApp.AppS.dto.SavingMovementDTO;
import com.miApp.AppS.service.SavingMovementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/savingMovements")
public class SavingMovementController {

    private final SavingMovementService savingMovementService;

    public SavingMovementController(SavingMovementService savingMovementService) {
        this.savingMovementService = savingMovementService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SavingMovementDTO>> getAllSavingMovements() {
        List<SavingMovementDTO> savingMovements = savingMovementService.getAllSavingMovements();
        return ResponseEntity.ok(savingMovements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SavingMovementDTO> getSavingMovementById(@PathVariable Long id) {
        SavingMovementDTO savingMovement = savingMovementService.getSavingMovementById(id);
        return ResponseEntity.ok(savingMovement);
    }

    @PostMapping("/create")
    public ResponseEntity<SavingMovementDTO> createSavingMovement(@RequestBody SavingMovementDTO savingMovementDTO) {
        SavingMovementDTO created = savingMovementService.createSavingMovement(savingMovementDTO);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SavingMovementDTO> updateSavingMovement(@PathVariable Long id, @RequestBody SavingMovementDTO savingMovementDTO) {
        SavingMovementDTO updated = savingMovementService.updateSavingMovement(id, savingMovementDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSavingMovement(@PathVariable Long id) {
        savingMovementService.deleteSavingMovement(id);
        return ResponseEntity.ok("Saving movement deleted successfully");
    }
}