package com.project.rideOptimiser.controller;

import com.project.rideOptimiser.dto.AssignDriverRequest;
import com.project.rideOptimiser.entity.Ride;
import com.project.rideOptimiser.entity.User;
import com.project.rideOptimiser.service.RideService;
import com.project.rideOptimiser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    @GetMapping
    public ResponseEntity<?> getAllRides(){
        List<Ride> rides = rideService.getAllRides();
        return ResponseEntity.ok(rides);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ride> getRideById(@PathVariable Long id){
        Ride ride= rideService.getRideById(id);

        return ResponseEntity.ok(ride);

    }

    @GetMapping("/location/{pickupLocation}")
    public ResponseEntity<List<Ride>> getRidesByPickupLocation(@PathVariable String pickupLocation) {
        List<Ride> rides = rideService.getRidesByPickupLocation(pickupLocation);
        return ResponseEntity.ok(rides);
    }

    @PostMapping
    public ResponseEntity<Ride> createRide(@RequestBody Ride ride) {
        Ride createdRide = rideService.createRide(ride);
        return ResponseEntity.ok(createdRide);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ride> updateRide(@PathVariable Long id, @RequestBody Ride ride) {
        Ride updatedRide = rideService.updateRide(id, ride);
        return ResponseEntity.ok(updatedRide);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRide(@PathVariable Long id) {
        rideService.deleteRide(id);
        return ResponseEntity.ok("Ride deleted successfully");
    }

    @PostMapping("/assign-driver")
    public ResponseEntity<Ride> assignNearestDriver(@RequestBody AssignDriverRequest request) {
        Ride assignedRide = rideService.assignNearestDriver(request.getRiderId(), request.getPickupLat(), request.getPickupLon());
        return ResponseEntity.ok(assignedRide);
    }


}


