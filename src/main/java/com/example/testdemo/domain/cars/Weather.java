package com.example.testdemo.domain.cars;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "car_weather")
public class Weather {

    /**
     * WData : 2017-06-06
     * type : 晴
     * temperature : 14~ 22
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private int id;
    @Column(name = "WData")
    private String WData;
    @Column(name = "type")
    private String type;
    @Column(name = "temperature")
    private String temperature;


}
