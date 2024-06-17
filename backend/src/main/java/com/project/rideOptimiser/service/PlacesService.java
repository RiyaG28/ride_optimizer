package com.project.rideOptimiser.service;

import com.project.rideOptimiser.entity.Places;
import com.project.rideOptimiser.repository.PlacesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlacesService {

    @Autowired
    private PlacesRepository placesRepository;

    public List<Places> addPlacesConnectionList(List<Places> places){
        return placesRepository.saveAll(places);
    }

    public List<String> listPlacesName(){
        return placesRepository.findAllNames();
    }
}
