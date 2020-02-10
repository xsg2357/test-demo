package com.example.testdemo.domain.cars;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Data
@Entity(name = "car_road")
public class CarRoad {

    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private  int id;
    @Column(name = "Status")
    private  int Status;
    @Column(name = "userId")
    private int userId;

}
