package com.miApp.AppS.service;

import com.miApp.AppS.dto.SavinPlanDTO;

import java.util.List;

public interface SavinPlanService {

    List <SavinPlanDTO> getAllSavinPlans();

    SavinPlanDTO findAllSavinPlans();
    SavinPlanDTO createSavinPlan(SavinPlanDTO savinPlanDTO);
    SavinPlanDTO getSavinPlanById(Long savinPlanId);
    SavinPlanDTO updateSavinPlan(Long savinPlanId, SavinPlanDTO savinPlanDTO);
    boolean deleteSavinPlan(Long savinPlanId);


}
