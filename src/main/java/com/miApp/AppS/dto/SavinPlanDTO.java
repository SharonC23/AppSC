package com.miApp.AppS.dto;

import com.miApp.AppS.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SavinPlanDTO {

    private Long idPlan;

    private Long userId;

    private String planName;

    private Double targetAmount;

    private String targetDate;

    private String description;

    private Boolean isActive;
}
