package com.project.rideOptimiser.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Connections {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    private String name;
    private int distance;

    @Override
    public String toString() {
        return "Connections{" +
                "name='" + name + '\'' +
                ", distance=" + distance +
                '}';
    }
}
