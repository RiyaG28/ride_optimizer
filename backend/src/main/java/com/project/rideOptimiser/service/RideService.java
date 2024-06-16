package com.project.rideOptimiser.service;

import com.project.rideOptimiser.entity.Ride;
import com.project.rideOptimiser.entity.User;
import com.project.rideOptimiser.enums.Role;
import com.project.rideOptimiser.enums.RideStatus;
import com.project.rideOptimiser.repository.RideRepository;
import com.project.rideOptimiser.repository.UserRepository;
import com.project.rideOptimiser.util.DistanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    public Ride getRideById(Long id) {
        return rideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ride not found with id: " + id));
    }

    public Ride createRide(Ride ride) {
        return rideRepository.save(ride);
    }

    public Ride updateRide(Long id, Ride updatedRide) {
        Ride ride = getRideById(id);
        ride.setDriver(updatedRide.getDriver());
        ride.setPickupLocation(updatedRide.getPickupLocation());
        ride.setDropLocation(updatedRide.getDropLocation());
        ride.setStatus(updatedRide.getStatus());
        return rideRepository.save(ride);
    }

    public void deleteRide(Long id) {
        rideRepository.deleteById(id);
    }

    public List<Ride> getRidesByPickupLocation(String pickupLocation) {
        return rideRepository.findByPickupLocation(pickupLocation);
    }

    public Ride assignNearestDriver(Long riderId, Double pickupLat, Double pickupLon) {
        // Retrieve the rider from the repository
//        User rider = userRepository.findById(riderId)
//                .orElseThrow(() -> new RuntimeException("Rider not found with id: " + riderId));

        Optional<User> optionalUser = userRepository.findById(riderId);
        User rider = optionalUser.orElseThrow(() -> new RuntimeException("Rider not found with id: " + riderId));



//        List<User> availableDrivers = userRepository.findByRoleAndAvailable(Role.DRIVER, true);
//
//        User nearestDriver = availableDrivers.stream()
//                .min(Comparator.comparingDouble(driver -> DistanceUtil.calculateDistance(
//                        pickupLat, pickupLon, driver.getLatitude(), driver.getLongitude())))
//                .orElseThrow(() -> new RuntimeException("No available drivers"));

        List<User> availableDrivers = userRepository.findByRoleAndAvailable(Role.DRIVER, true);
        User nearestDriver = null;
        double minDistance = Double.MAX_VALUE;

        for (User driver : availableDrivers) {
            double distance = DistanceUtil.calculateDistance(pickupLat, pickupLon, driver.getLatitude(), driver.getLongitude());
            if (distance < minDistance) {
                minDistance = distance;
                nearestDriver = driver;
            }
        }

        if(nearestDriver==null){
            throw new RuntimeException("No available drivers");
        }

        Ride ride = new Ride();
        ride.setRider(rider);
        ride.setDriver(nearestDriver);
        ride.setPickupLocation("Lat: " + pickupLat + ", Lon: " + pickupLon);
        ride.setStatus(RideStatus.REQUESTED);
        ride.setPickupLat(pickupLat);
        ride.setPickupLon(pickupLon);

        return rideRepository.save(ride);
    }

}
