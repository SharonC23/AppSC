package com.miApp.AppS.controller;


import com.miApp.AppS.dto.CapitalizationSettingsDTO;
import com.miApp.AppS.service.CapitalizationSettingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/capitalizationSettings")
public class CapitalizationSettingsController {

    private final CapitalizationSettingsService capitalizationSettingsService;


    public CapitalizationSettingsController(CapitalizationSettingsService capitalizationSettingsService) {
        this.capitalizationSettingsService = capitalizationSettingsService;
    }

    @GetMapping ("/all")
    public ResponseEntity <List<CapitalizationSettingsDTO>> getAllCapitalizationSettings() {
        List<CapitalizationSettingsDTO> settings = capitalizationSettingsService.getAllCapitalizationSettings();
        return ResponseEntity.ok(settings);
    }

    @PostMapping ("/create")
    public ResponseEntity <CapitalizationSettingsDTO> createCapitalizationSettings(@RequestBody CapitalizationSettingsDTO capitalizationSettingsDTO) {
        CapitalizationSettingsDTO createdSettings = capitalizationSettingsService.createCapitalizationSettings(capitalizationSettingsDTO);
        return ResponseEntity.ok(createdSettings);
    }

}
