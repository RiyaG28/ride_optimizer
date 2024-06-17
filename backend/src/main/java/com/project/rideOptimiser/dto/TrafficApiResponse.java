package com.project.rideOptimiser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrafficApiResponse {

    private Route[] routes;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Route {
    // Define attributes as needed to map JSON response
    // Example: Steps, traffic information, etc.
}
