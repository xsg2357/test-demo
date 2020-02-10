package com.example.testdemo.domain.cars;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Data
@Entity(name = "car_light_sensor")
public class CarLightSensor {

    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private int id;
    @Column(name = "Down")
    private int Down;
    @Column(name = "Up")
    private int Up;
    @Column(name = "userId")
    private int userId;

}
