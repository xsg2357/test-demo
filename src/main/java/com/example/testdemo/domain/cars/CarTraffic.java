package com.example.testdemo.domain.cars;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Data
@Entity(name = "car_traffic")
public class CarTraffic {

    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private int Id;

    @Column(name = "trafficLightId")
    private  String  trafficLightId;
    @Column(name = "Status")
    private  String  Status;

    @Column(name = "RedTime")
    private int RedTime;
    @Column(name = "GreenTime")
    private int GreenTime;
    @Column(name = "YellowTime")
    private int YellowTime;
}
