package com.miApp.AppS.Impl;


import com.miApp.AppS.dto.FinancialProjectionDTO;
import com.miApp.AppS.entity.FinancialProjection;
import com.miApp.AppS.entity.User;
import com.miApp.AppS.exceptions.CustomException;
import com.miApp.AppS.repository.FinancialProjectionRepository;
import com.miApp.AppS.repository.UserRepository;
import com.miApp.AppS.service.FinancialProjectionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinancialProjectionServiceImpl implements FinancialProjectionService {

    private final FinancialProjectionRepository financialProjectionRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public FinancialProjectionServiceImpl(FinancialProjectionRepository financialProjectionRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.financialProjectionRepository = financialProjectionRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<FinancialProjectionDTO> getAllFinancialProjections() {
        List<FinancialProjection> financialProjections = financialProjectionRepository.findAll();
        return financialProjections.stream()
                .map(financialProjection -> modelMapper.map(financialProjection, FinancialProjectionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FinancialProjectionDTO findAllFinancialProjections() {
        return null;
    }

    @Override
    public FinancialProjectionDTO getFinancialProjectionById(Long financialProjectionId) {
        FinancialProjection financialProjection = financialProjectionRepository.findById(financialProjectionId)
                .orElseThrow(() -> new CustomException("Financial projection not found with id: " + financialProjectionId));
        return modelMapper.map(financialProjection, FinancialProjectionDTO.class);
    }

    @Override
    public FinancialProjectionDTO createFinancialProjection(FinancialProjectionDTO financialProjectionDTO) {
        FinancialProjection financialProjection = modelMapper.map(financialProjectionDTO, FinancialProjection.class);
        if (financialProjectionDTO.getUserId() != null) {
            User user = userRepository.findById(financialProjectionDTO.getUserId())
                    .orElseThrow(() -> new CustomException("User not found with id: " + financialProjectionDTO.getUserId()));
            financialProjection.setUser(user);
        }
        financialProjection = financialProjectionRepository.save(financialProjection);
        return modelMapper.map(financialProjection, FinancialProjectionDTO.class);
    }

    @Override
    public FinancialProjectionDTO updateFinancialProjection(Long financialProjectionId, FinancialProjectionDTO financialProjectionDTO) {
        if (financialProjectionRepository.existsById(financialProjectionId)) {
            FinancialProjection financialProjection = modelMapper.map(financialProjectionDTO, FinancialProjection.class);
            financialProjection.setIdProjection(financialProjectionId);
            financialProjection = financialProjectionRepository.save(financialProjection);
            return modelMapper.map(financialProjection, FinancialProjectionDTO.class);
        } else {
            throw new CustomException("Financial projection not found with id: " + financialProjectionId);
        }
    }

    @Override
    public void deleteFinancialProjection(Long financialProjectionId) {
        if (!financialProjectionRepository.existsById(financialProjectionId)) {
            financialProjectionRepository.deleteById(financialProjectionId);
        } else {
            throw new CustomException("Financial projection not found with id: " + financialProjectionId);
        }
    }
}
