package com.miApp.AppS.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name= "savin_plans")
@Data
public class SavinPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlan;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "plan_name", nullable = false, length = 100)
    private String planName;

    @Column(name = "target_amount", nullable = false)
    private Double targetAmount;

    @Column(name = "target_date", nullable = false)
    private String targetDate;

    @Column(name = "description", length = 255)
    private String description;

    @Column (name = "is_active", nullable = false)
    private Boolean isActive;



}
