package com.project.rideOptimiser.controller;

import com.project.rideOptimiser.entity.Places;
import com.project.rideOptimiser.service.DijkstrasAlgorithmService;
import com.project.rideOptimiser.service.PlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/places")
@CrossOrigin(origins= "http://localhost:3000")
public class PlacesController {

    @Autowired
    private PlacesService placesService;

    @Autowired
    private DijkstrasAlgorithmService dijkstrasAlgorithmService;


    @PostMapping("/add-placeslist")
    public ResponseEntity<List<Places>> addPlacesList(@RequestBody List<Places> places){

        List<Places> placesList= placesService.addPlacesConnectionList(places);

        return ResponseEntity.ok(placesList);

    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllPlaceNames() {
        List<String> placeNames = placesService.listPlacesName();
        return ResponseEntity.ok(placeNames);
    }

    @CrossOrigin(origins= "http://localhost:3000")
    @PostMapping("/shortestPath")
    public ResponseEntity<?> findShortestPath(@RequestBody Map<String, String> requestBody){

        String start = requestBody.get("start");
        String end = requestBody.get("end");

        List<String> shortestPath = dijkstrasAlgorithmService.findShortestPath(start,end);

        return ResponseEntity.ok(shortestPath);
    }

}
