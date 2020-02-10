package com.example.testdemo.domain.cars;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Data
@Entity(name = "car_pattern")
public class CarPattern {

    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private int id;
    @Column(name = "controlMode")
    private String controlMode;
    @Column(name = "Action")
    private String Action;

}
