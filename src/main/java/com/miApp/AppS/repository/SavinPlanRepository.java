package com.miApp.AppS.repository;


import com.miApp.AppS.entity.SavinPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavinPlanRepository  extends JpaRepository<SavinPlan, Long> {

    SavinPlan findByIdPlan(Long idPlan);


}
