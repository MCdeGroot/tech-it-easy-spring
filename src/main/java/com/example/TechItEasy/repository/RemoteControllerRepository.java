package com.example.TechItEasy.repository;

import com.example.TechItEasy.model.RemoteController;
import com.example.TechItEasy.model.Television;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemoteControllerRepository extends JpaRepository<RemoteController, Long> {
}
