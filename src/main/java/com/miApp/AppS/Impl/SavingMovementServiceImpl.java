package com.miApp.AppS.Impl;

import com.miApp.AppS.dto.SavingMovementDTO;
import com.miApp.AppS.entity.SavinPlan;
import com.miApp.AppS.entity.SavingMovement;
import com.miApp.AppS.exceptions.CustomException;
import com.miApp.AppS.repository.SavinPlanRepository;
import com.miApp.AppS.repository.SavingMovementRepository;
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

    @Autowired
    public SavingMovementServiceImpl(SavingMovementRepository savingMovementRepository,
                                     ModelMapper modelMapper,
                                     SavinPlanRepository savinPlanRepository) {
        this.savingMovementRepository = savingMovementRepository;
        this.modelMapper = modelMapper;
        this.savinPlanRepository = savinPlanRepository;
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
        SavingMovement savingMovement = modelMapper.map(savingMovementDTO, SavingMovement.class);

        if (savingMovementDTO.getIdSaving() != null) {
            SavinPlan savinPlan = savinPlanRepository.findById(savingMovementDTO.getIdSaving())
                    .orElseThrow(() -> new CustomException("Saving plan not found with id: " + savingMovementDTO.getIdSaving()));
            savingMovement.setSavinPlan(savinPlan);
        }

        SavingMovement createdSavingMovement = savingMovementRepository.save(savingMovement);
        return modelMapper.map(createdSavingMovement, SavingMovementDTO.class);
    }

    @Override
    public SavingMovementDTO updateSavingMovement(Long savingMovementId, SavingMovementDTO savingMovementDTO) {
        if (savingMovementRepository.existsById(savingMovementId)) {
            SavingMovement savingMovement = modelMapper.map(savingMovementDTO, SavingMovement.class);
            savingMovement.setIdSaving(savingMovementId);
            savingMovement = savingMovementRepository.save(savingMovement);
            return modelMapper.map(savingMovement, SavingMovementDTO.class);
        } else {
            throw new CustomException("Saving movement not found with id: " + savingMovementId);
        }
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
