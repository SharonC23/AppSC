package com.miApp.AppS.Impl;


import com.miApp.AppS.dto.CapitalizationSettingsDTO;
import com.miApp.AppS.entity.CapitalizationSettings;
import com.miApp.AppS.entity.User;
import com.miApp.AppS.exceptions.CustomException;
import com.miApp.AppS.repository.CapitalizationSettingsRepository;
import com.miApp.AppS.repository.UserRepository;
import com.miApp.AppS.service.CapitalizationSettingsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class CapitalizationServiceImpl implements CapitalizationSettingsService {


    private final CapitalizationSettingsRepository capitalizationSettingsRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;




    @Autowired
    public CapitalizationServiceImpl(CapitalizationSettingsRepository capitalizationSettingsRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.capitalizationSettingsRepository = capitalizationSettingsRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    @Override
    public List<CapitalizationSettingsDTO> getAllCapitalizationSettings() {
        List<CapitalizationSettings> settings = capitalizationSettingsRepository.findAll();
        return settings.stream()
                .map(setting -> modelMapper.map(setting, CapitalizationSettingsDTO.class))
                .collect(java.util.stream.Collectors.toList());
    }


    @Override
    public CapitalizationSettingsDTO findAllCapitalizationSettings() {
        return null;
    }


    @Override
    public CapitalizationSettingsDTO getCapitalizationSettingsById(Long idCapitalizationSettings) {
        CapitalizationSettings settings = capitalizationSettingsRepository.findById(idCapitalizationSettings)
                .orElseThrow(() -> new CustomException("Capitalization settings not found with id: " + idCapitalizationSettings));
        return modelMapper.map(settings, CapitalizationSettingsDTO.class);
    }


    @Override
    public CapitalizationSettingsDTO createCapitalizationSettings(CapitalizationSettingsDTO capitalizationSettingsDTO) {
        CapitalizationSettings capitalizationSettings = modelMapper.map(capitalizationSettingsDTO, CapitalizationSettings.class);
        if (capitalizationSettingsDTO.getUserId() != null) {
            User user = userRepository.findById(capitalizationSettingsDTO.getUserId())
                    .orElseThrow(() -> new CustomException("User not found with id: " + capitalizationSettingsDTO.getUserId()));
            capitalizationSettings.setUser(user);
        }
        capitalizationSettings = capitalizationSettingsRepository.save(capitalizationSettings);
        return modelMapper.map(capitalizationSettings, CapitalizationSettingsDTO.class);
    }




    @Override
    public CapitalizationSettingsDTO updateCapitalizationSettings(Long idCapitalizationSettings, CapitalizationSettingsDTO capitalizationSttingsDTO) {
        if (capitalizationSettingsRepository.existsById(idCapitalizationSettings)) {
            CapitalizationSettings capitalizationSettings = modelMapper.map(capitalizationSttingsDTO, CapitalizationSettings.class);
            capitalizationSettings.setIdCapitalization(idCapitalizationSettings);
            capitalizationSettings = capitalizationSettingsRepository.save(capitalizationSettings);
            return modelMapper.map(capitalizationSettings, CapitalizationSettingsDTO.class);
        } else {
            throw new CustomException("Capitalization settings not found with id: " + idCapitalizationSettings);
        }
    }


    @Override
    public boolean deleteCapitalizationSettings(Long idCapitalizationSettings) {
        if(capitalizationSettingsRepository.existsById(idCapitalizationSettings)){
            capitalizationSettingsRepository.deleteById(idCapitalizationSettings);
        } else {
            throw new CustomException("Capitalization settings not found with id: " + idCapitalizationSettings);
        }
        capitalizationSettingsRepository.deleteById(idCapitalizationSettings);
        return true;


    }
}
