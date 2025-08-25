package com.miApp.AppS.Impl;

import com.miApp.AppS.dto.SavingMovementDTO;
import com.miApp.AppS.entity.SavinPlan;
import com.miApp.AppS.entity.SavingMovement;
import com.miApp.AppS.entity.User;
import com.miApp.AppS.exceptions.CustomException;
import com.miApp.AppS.repository.SavinPlanRepository;
import com.miApp.AppS.repository.SavingMovementRepository;
import com.miApp.AppS.repository.UserRepository;
import com.miApp.AppS.service.SavingMovementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SavingMovementServiceImpl implements SavingMovementService {

    private final SavingMovementRepository savingMovementRepository;
    private final ModelMapper modelMapper;
    private final SavinPlanRepository savinPlanRepository;
    private final UserRepository userRepository;

    @Autowired
    public SavingMovementServiceImpl(SavingMovementRepository savingMovementRepository,
                                     ModelMapper modelMapper,
                                     SavinPlanRepository savinPlanRepository, UserRepository userRepository) {
        this.savingMovementRepository = savingMovementRepository;
        this.modelMapper = modelMapper;
        this.savinPlanRepository = savinPlanRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<SavingMovementDTO> getAllSavingMovements() {
        List<SavingMovement> savingMovements = savingMovementRepository.findAll();
        return savingMovements.stream()
                .map(savingMovement -> modelMapper.map(savingMovement, SavingMovementDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SavingMovementDTO findAllSavingMovements() {
        return null; // ⚠️ este método parece que sobra, revisa si lo necesitas realmente
    }

    @Override
    public SavingMovementDTO getSavingMovementById(Long savingMovementId) {
        SavingMovement savingMovement = savingMovementRepository.findById(savingMovementId)
                .orElseThrow(() -> new CustomException("Saving movement not found with id: " + savingMovementId));
        return modelMapper.map(savingMovement, SavingMovementDTO.class);
    }

    @Override
    public SavingMovementDTO createSavingMovement(SavingMovementDTO savingMovementDTO) {
        if (savingMovementDTO.getPlanId() == null) {
            throw new CustomException("Saving plan id is required");
        }

        SavingMovement savingMovement = modelMapper.map(savingMovementDTO, SavingMovement.class);

        SavinPlan savinPlan = savinPlanRepository.findById(savingMovementDTO.getPlanId())
                .orElseThrow(() -> new CustomException("Saving plan not found with id: " + savingMovementDTO.getIdSaving()));

        savingMovement.setSavinPlan(savinPlan);

        SavingMovement createdSavingMovement = savingMovementRepository.save(savingMovement);
        return modelMapper.map(createdSavingMovement, SavingMovementDTO.class);
    }


    @Override
    public SavingMovementDTO updateSavingMovement(Long savingMovementId, SavingMovementDTO savingMovementDTO) {
        SavingMovement existingMovement = savingMovementRepository.findById(savingMovementId)
                .orElseThrow(() -> new CustomException("Saving movement not found with id: " + savingMovementId));

        existingMovement.setType(savingMovementDTO.getType());
        existingMovement.setDescriptionMovement(savingMovementDTO.getDescriptionMovement());
        existingMovement.setAmount(savingMovementDTO.getAmount());
        existingMovement.setDate(savingMovementDTO.getDate());

        if (savingMovementDTO.getPlanId() != null) {
            SavinPlan savinPlan = savinPlanRepository.findById(savingMovementDTO.getPlanId())
                    .orElseThrow(() -> new CustomException("Saving plan not found with id: " + savingMovementDTO.getPlanId()));
            existingMovement.setSavinPlan(savinPlan);
        }

        if (savingMovementDTO.getUserId() != null) {
            User user = userRepository.findById(savingMovementDTO.getUserId())
                    .orElseThrow(() -> new CustomException("User not found with id: " + savingMovementDTO.getUserId()));
            existingMovement.setUser(user);
        }

        SavingMovement updated = savingMovementRepository.save(existingMovement);
        return modelMapper.map(updated, SavingMovementDTO.class);
    }


    @Override
    public void deleteSavingMovement(Long savingMovementId) {
        if (savingMovementRepository.existsById(savingMovementId)) {
            savingMovementRepository.deleteById(savingMovementId);
        } else {
            throw new CustomException("Saving movement not found with id: " + savingMovementId);
        }
    }
}
