package com.miApp.AppS.controller;


import com.miApp.AppS.dto.SavinPlanDTO;
import com.miApp.AppS.service.SavinPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/SavinPlans")

public class SavinPlanController {

    private final SavinPlanService savinPlanService;


    public SavinPlanController(SavinPlanService savinPlanService) {
        this.savinPlanService = savinPlanService;
    }

    @GetMapping ("/all")
    public ResponseEntity <List<SavinPlanDTO>> getAllSavinPlans() {
        List<SavinPlanDTO> savinPlans = savinPlanService.getAllSavinPlans();
        return ResponseEntity.ok(savinPlans);
    }

    @GetMapping ("/{savinPlanId}")
    public ResponseEntity <SavinPlanDTO> getSavinPlanById(Long savinPlanId) {
        SavinPlanDTO savinPlan = savinPlanService.getSavinPlanById(savinPlanId);
        return ResponseEntity.ok(savinPlan);
    }

    @PostMapping ("/create")
    public ResponseEntity <SavinPlanDTO> createSavinPlan(@RequestBody SavinPlanDTO savinPlanDTO) {
        SavinPlanDTO createdSavinPlan = savinPlanService.createSavinPlan(savinPlanDTO);
        return ResponseEntity.ok(createdSavinPlan);
    }

    @PutMapping ("/update/{savinPlanId}")
    public ResponseEntity<SavinPlanDTO> updateTransaction(@PathVariable Long savinPlanId, @RequestBody SavinPlanDTO savinPlanDTO) {
        SavinPlanDTO updatedSavinPlan = savinPlanService.updateSavinPlan(savinPlanId, savinPlanDTO);
        return ResponseEntity.ok(updatedSavinPlan);
    }

    @DeleteMapping ("/delete/{savinPlanId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long savinPlanId){
        savinPlanService.deleteSavinPlan(savinPlanId);
        return ResponseEntity.noContent().build();
    }


}
