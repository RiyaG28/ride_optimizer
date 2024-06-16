package com.project.rideOptimiser.repository;

import com.project.rideOptimiser.entity.User;
import com.project.rideOptimiser.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);
    Optional<User> findById(Long id); // Updated to return Optional<User>
    List<User> findByRole(Role role);
    List<User> findByRoleAndAvailable(Role role, boolean available);
}