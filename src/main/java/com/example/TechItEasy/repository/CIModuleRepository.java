package com.example.TechItEasy.repository;

import com.example.TechItEasy.model.CIModule;
import com.example.TechItEasy.model.Television;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CIModuleRepository extends JpaRepository<CIModule, Long> {
}
