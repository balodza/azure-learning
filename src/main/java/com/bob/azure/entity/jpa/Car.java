package com.bob.azure.entity.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Car")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "model", nullable = false, length = 100)
    private String model;

    @Column(name = "year", nullable = false, length = 100)
    private int year;

    @Column(name = "version", length = 255)
    private String version;

    @Column(name = "engine_pistons")
    private int engine_pistons;

    @Column(name = "engine_volume")
    private double engine_volume;

    @Column(name = "engine_power")
    private int engine_power;

    @ManyToOne
    @JoinColumn(name = "make_id")
    private Make make;
}
