package com.miApp.AppS.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class SavingMovementDTO {

    private Long idSaving;

    private Long planId;

    private Long userId;

    private String type;

    private String descriptionMovement;

    private Double amount;

    private LocalDate date;



}
