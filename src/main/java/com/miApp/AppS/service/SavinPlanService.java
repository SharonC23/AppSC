package com.miApp.AppS.service;

import com.miApp.AppS.dto.SavinPlanDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SavinPlanService {

    List <SavinPlanDTO> getAllSavinPlans();

    SavinPlanDTO findAllSavinPlans();
    SavinPlanDTO createSavinPlan(SavinPlanDTO savinPlanDTO);
    SavinPlanDTO getSavinPlanById(Long savinPlanId);
    SavinPlanDTO updateSavinPlan(Long savinPlanId, SavinPlanDTO savinPlanDTO);
    boolean deleteSavinPlan(Long savinPlanId);


}
