package com.project.rideOptimiser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignDriverRequest {
    private Long riderId;
    private Double pickupLat;
    private Double pickupLon;
}
