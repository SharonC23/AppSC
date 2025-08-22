package com.miApp.AppS.repository;


import com.miApp.AppS.entity.CapitalizationSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapitalizationSettingsRepository extends JpaRepository <CapitalizationSettings, Long> {

    CapitalizationSettings findByIdCapitalization(Long idCapitalization);
}


