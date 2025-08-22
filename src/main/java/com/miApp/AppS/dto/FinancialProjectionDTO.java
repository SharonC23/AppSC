package com.miApp.AppS.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class FinancialProjectionDTO {

    private Long idProjection;

    private Long userId;

    private String projectionType;

    private String descriptionProjection;

    private Double amount;

    private LocalDateTime projectionDate;

    private LocalDate createdAt;

    private LocalDate updatedAt;

}
