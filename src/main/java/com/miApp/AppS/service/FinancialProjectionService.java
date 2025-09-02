package com.miApp.AppS.service;

import com.miApp.AppS.dto.FinancialProjectionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FinancialProjectionService {

    List <FinancialProjectionDTO> getAllFinancialProjections();

    FinancialProjectionDTO findAllFinancialProjections();

    FinancialProjectionDTO createFinancialProjection(FinancialProjectionDTO financialProjectionDTO);
    FinancialProjectionDTO getFinancialProjectionById(Long financialProjectionId);
    FinancialProjectionDTO updateFinancialProjection(Long financialProjectionId, FinancialProjectionDTO financialProjectionDTO);
    void deleteFinancialProjection(Long financialProjectionId);


}
