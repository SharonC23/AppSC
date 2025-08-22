package com.miApp.AppS.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "saving_movements")
@Data
public class SavingMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_saving", nullable = false, unique = true)
    private Long idSaving;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    // Relación con la entidad User
    private User user;

    @ManyToOne
    @JoinColumn (name = "id_plan", nullable = false)
    // Relación con la entidad SavinPlan
    private SavinPlan savinPlan;

    @Column(name = "type", nullable = false, length = 50)
    private String type; // Tipo de movimiento (ingreso, gasto, etc.)

    @Column(name = "description_movement", length = 255)
    private String descriptionMovement; // Descripción del movimiento de ahorro

    @Column(name = "amount", nullable = false)
    private Double amount; // Monto del movimiento de ahorro

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @PrePersist
    public void prePersist() {
        if (date == null) {
            date = LocalDate.now();
        }
    }
}
