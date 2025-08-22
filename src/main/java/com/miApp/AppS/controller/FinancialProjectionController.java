package com.miApp.AppS.controller;


import com.miApp.AppS.dto.FinancialProjectionDTO;
import com.miApp.AppS.service.FinancialProjectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/financialProjections")
public class FinancialProjectionController {

    private final FinancialProjectionService financialProjectionService;

    public FinancialProjectionController(FinancialProjectionService financialProjectionService) {
        this.financialProjectionService = financialProjectionService;
    }

    @GetMapping ("/all")
    public ResponseEntity<List<FinancialProjectionDTO>> getAllFinancialProjections() {
        List<FinancialProjectionDTO> financialProjections = financialProjectionService.getAllFinancialProjections();
        return ResponseEntity.ok(financialProjections);
    }

    @GetMapping ("/{financialProjectionId}")
    public ResponseEntity<FinancialProjectionDTO> getFinancialProjectionById(Long financialProjectionId) {
        FinancialProjectionDTO financialProjection = financialProjectionService.getFinancialProjectionById(financialProjectionId);
        return ResponseEntity.ok(financialProjection);
    }

    @PostMapping("/create")
    public ResponseEntity<FinancialProjectionDTO> createFinancialProjection( @RequestBody FinancialProjectionDTO financialProjectionDTO) {
        financialProjectionService.createFinancialProjection(financialProjectionDTO);
        return ResponseEntity.ok(financialProjectionDTO);
    }

    @PutMapping ("/update/{financialProjectionId}")
    public ResponseEntity<FinancialProjectionDTO> updateTransaction(@PathVariable Long financialProjectionId, @RequestBody FinancialProjectionDTO financialProjectionDTO) {
        FinancialProjectionDTO updatedFinancialProjection = financialProjectionService.updateFinancialProjection(financialProjectionId, financialProjectionDTO);
        return ResponseEntity.ok(updatedFinancialProjection);
    }

    @DeleteMapping("/delete/{financialProjectionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long financialProjectionId){
        financialProjectionService.deleteFinancialProjection(financialProjectionId);
        return ResponseEntity.noContent().build();
    }


}
