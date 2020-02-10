package com.example.testdemo.domain.cars;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "car_route_detail")
public class CarRouteDetail {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private  int Id;

    @Column(name = "Path")
    private  String Path;


}
