package com.example.testdemo.domain.cars;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Data
@Entity(name = "car_etc")
public class CarETC {

    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private int id;
    @Column(name = "RateType")
    private  String RateType;
    @Column(name = "Money")
    private  int Money;
    @Column(name = "userId")
    private int userId;
}
