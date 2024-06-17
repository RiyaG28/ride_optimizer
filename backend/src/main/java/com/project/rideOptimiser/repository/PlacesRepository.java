package com.project.rideOptimiser.repository;

import com.project.rideOptimiser.entity.Places;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlacesRepository extends JpaRepository<Places, Long> {

    @Query("SELECT p.name FROM Places p")
    List<String> findAllNames();
}
