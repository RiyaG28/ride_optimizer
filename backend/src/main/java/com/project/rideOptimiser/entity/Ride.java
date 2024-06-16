package com.project.rideOptimiser.entity;

import com.project.rideOptimiser.enums.RideStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rider_id", nullable = false)
    private User rider;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private User driver;

    @Column
    private String pickupLocation;

    @Column String dropLocation;

    @Column(nullable = false)
    private Double pickupLat;

    @Column(nullable = false)
    private Double pickupLon;

    @Column
    private Double dropLat;

    @Column
    private Double dropLon;

    @Enumerated(EnumType.STRING)
    private RideStatus status; // Enum RideStatus: REQUESTED, ACCEPTED, COMPLETED, CANCELLED

    private LocalDateTime assignedTime;
    private LocalDateTime estimatedArrival;

    // Constructors, getters, setters
}
