package com.project.rideOptimiser.repository;

import com.project.rideOptimiser.entity.Ride;
import com.project.rideOptimiser.entity.User;
import com.project.rideOptimiser.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
//    List<Ride> findByRole(Role role);
   // List<Ride> findByDriver(User driver);
    // Additional methods as needed

    List<Ride> findByPickupLocation(String pickupLocation);
}

