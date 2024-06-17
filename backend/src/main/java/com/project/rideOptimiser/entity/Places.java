package com.project.rideOptimiser.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Places {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "places_connections", joinColumns = @JoinColumn(name = "place_id"))
    private List<Connections> connections;

    @Override
    public String toString() {
        return "Places{" +
                "name='" + name + '\'' +
                ", connections=" + connections +
                '}';
    }
}
