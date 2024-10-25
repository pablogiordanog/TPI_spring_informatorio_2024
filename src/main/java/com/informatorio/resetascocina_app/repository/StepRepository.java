package com.informatorio.resetascocina_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.informatorio.resetascocina_app.domain.Step;

import java.util.UUID;

public interface StepRepository extends JpaRepository<Step, UUID>{
}
