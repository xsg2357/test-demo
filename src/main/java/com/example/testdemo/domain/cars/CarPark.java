package com.example.testdemo.domain.cars;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "car_park")
public class CarPark {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private  int id;
    @Column(name = "RateType")
    private  String RateType;
    @Column(name = "Money")
    private  int Money;
    @Column(name = "satus")
    private  int satus;
    @Column(name = "userId")
    private  int userId;

}
