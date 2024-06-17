package com.project.rideOptimiser.service;

import com.project.rideOptimiser.entity.Connections;
import com.project.rideOptimiser.entity.Places;
import com.project.rideOptimiser.repository.PlacesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DijkstrasAlgorithmService {

    @Autowired
    private PlacesRepository placesRepository;

    public List<String> findShortestPath(String startPlaceName, String endPlaceName){

        List<Places> placesList = placesRepository.findAll();
        Map<String, List<Connections>> graph = buildGraph(placesList);

        System.out.println("Graph contents: " + graph);
        System.out.println("Start place: " + startPlaceName);
        System.out.println("End place: " + endPlaceName);

        if (!graph.containsKey(startPlaceName) || !graph.containsKey(endPlaceName)) {
            throw new IllegalArgumentException("Start or end place not found in the graph.");
        }

        //Apply Dijkstra's Algorithm

        Map<String,Integer> shortestDistances= new HashMap<>();
        Map<String,String> predecessors = new HashMap<>();
        PriorityQueue<String> priorityQueue= new PriorityQueue<>(Comparator.comparingInt(shortestDistances::get));

        //Initialise distances
        for (String placeName : graph.keySet()) {
            shortestDistances.put(placeName, placeName.equals(startPlaceName) ? 0 : Integer.MAX_VALUE);
            priorityQueue.add(placeName);
        }

        //process the nodes

        while(!priorityQueue.isEmpty()){

            String currPlace= priorityQueue.poll();

            if(shortestDistances.get(currPlace)== Integer.MAX_VALUE)break;

            for(Connections connection: graph.get(currPlace)){

                String connectedPlace= connection.getName();
                int distance= connection.getDistance();

                int newDistance= shortestDistances.get(currPlace) + distance;

                if(newDistance< shortestDistances.get(connectedPlace)){
                    shortestDistances.put(connectedPlace, newDistance);
                    predecessors.put(connectedPlace, currPlace);
                    priorityQueue.add(connectedPlace);
                }
            }
        }

        // Build path from startPlaceName to endPlaceName
        LinkedList<String> path = new LinkedList<>();
        String place = endPlaceName;
        while (place != null) {
            path.addFirst(place);
            place = predecessors.get(place);
        }

        if (path.size() == 1 && !path.getFirst().equals(startPlaceName)) {
            // No path found
            throw new IllegalArgumentException("No path found between start and end places.");
        }

        return path;

    }

    private Map<String, List<Connections>> buildGraph(List<Places> placesList){

        Map<String, List<Connections>> graph = new HashMap<>();

        for(Places place: placesList){
            graph.put(place.getName(), place.getConnections());
        }

        return graph;
    }
}



