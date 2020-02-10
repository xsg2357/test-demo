package com.example.testdemo.domain.cars;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "car_info")
public class CarModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private  int id;
    @Column(name = "CarAction")
    private  String CarAction;
    @Column(name = "Balance")
    private  double Balance;
    @Column(name = "CarSpeed")
    private  int CarSpeed;
    @JsonIgnore
    @Column(name = "PathId")
    private  int PathId;
    @Column(name = "CarLocationX")
    private  double CarLocationX;
    @Column(name = "CarLocationY")
    private  double CarLocationY;

}
