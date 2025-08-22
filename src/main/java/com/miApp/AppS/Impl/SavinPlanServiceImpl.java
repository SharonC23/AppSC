package com.miApp.AppS.Impl;

import com.miApp.AppS.dto.SavinPlanDTO;
import com.miApp.AppS.entity.SavinPlan;
import com.miApp.AppS.entity.User;
import com.miApp.AppS.exceptions.CustomException;
import com.miApp.AppS.repository.SavinPlanRepository;
import com.miApp.AppS.repository.UserRepository;
import com.miApp.AppS.service.SavinPlanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SavinPlanServiceImpl implements SavinPlanService {

    private final SavinPlanRepository savinPlanRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public SavinPlanServiceImpl(SavinPlanRepository savinPlanRepository,
                                ModelMapper modelMapper,
                                UserRepository userRepository) {
        this.savinPlanRepository = savinPlanRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<SavinPlanDTO> getAllSavinPlans() {
        List<SavinPlan> savinPlans = savinPlanRepository.findAll();
        return savinPlans.stream()
                .map(savinPlan -> modelMapper.map(savinPlan, SavinPlanDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SavinPlanDTO findAllSavinPlans() {
        return null;
    }


    @Override
    public SavinPlanDTO getSavinPlanById(Long savinPlanId) {
        SavinPlan savinPlan = savinPlanRepository.findById(savinPlanId)
                .orElseThrow(() -> new CustomException("Savin plan not found with id: " + savinPlanId));
        return modelMapper.map(savinPlan, SavinPlanDTO.class);
    }

    @Override
    public SavinPlanDTO createSavinPlan(SavinPlanDTO savinPlanDTO) {
        SavinPlan savinPlan = modelMapper.map(savinPlanDTO, SavinPlan.class);
        if (savinPlanDTO.getUserId() != null) {
            User user = userRepository.findById(savinPlanDTO.getUserId())
                    .orElseThrow(() -> new CustomException("User not found with id: " + savinPlanDTO.getUserId()));
            savinPlan.setUser(user);
        }
        savinPlan = savinPlanRepository.save(savinPlan);
        return modelMapper.map(savinPlan, SavinPlanDTO.class);
    }

    @Override
    public SavinPlanDTO updateSavinPlan(Long savinPlanId, SavinPlanDTO savinPlanDTO) {
        SavinPlan savinPlan = savinPlanRepository.findById(savinPlanId)
                .orElseThrow(() -> new CustomException("Savin plan not found with id: " + savinPlanId));
        savinPlan.setPlanName(savinPlanDTO.getPlanName());
        savinPlan.setIsActive(savinPlanDTO.getIsActive());
        return modelMapper.map(savinPlan, SavinPlanDTO.class);
    }

    @Override
    public boolean deleteSavinPlan(Long savinPlanId) {
        if (!savinPlanRepository.existsById(savinPlanId)) {
            throw new CustomException("Savin plan not found with id: " + savinPlanId);
        }
        savinPlanRepository.deleteById(savinPlanId);
        return true;
    }
}
