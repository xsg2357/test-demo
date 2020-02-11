package com.example.testdemo.domain.cars;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "car_bus_station")
public class CarBusStation {


    /**
     * Distance : 2250
     * BusId : 1
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @JsonIgnore
    private int id;
    @Column(name = "Distance")
    private int Distance;
    @Column(name = "BusId")
    private int BusId;

    @Column(name = "userId")
    @JsonIgnore
    private int userId;

}
