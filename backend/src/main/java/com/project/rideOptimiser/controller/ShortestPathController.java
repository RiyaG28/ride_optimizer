//package com.project.rideOptimiser.controller;
//
//
//import com.project.rideOptimiser.service.DijkstrasAlgorithmService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/places/shortestPath")
//@CrossOrigin(origins = "http://localhost:3000")
//public class ShortestPathController {
//
//    @Autowired
//    private DijkstrasAlgorithmService dijkstrasAlgorithmService;
//
//    @GetMapping
//    public ResponseEntity<?> findShortestPath(@RequestBody Map<String, String> requestBody){
//
//        String start = requestBody.get("start");
//        String end = requestBody.get("end");
//
//        List<String> shortestPath = dijkstrasAlgorithmService.findShortestPath(start,end);
//
//        return ResponseEntity.ok(shortestPath);
//    }
//
//
//}
