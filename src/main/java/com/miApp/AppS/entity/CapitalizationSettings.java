package com.miApp.AppS.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "CapitalizationSettings")
@Data

public class CapitalizationSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_CapitalizationS", nullable = false, unique = true)
    private Long idCapitalization;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    // Relación con la entidad User
    private User user;

    @Column (name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

}
