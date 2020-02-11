package com.example.testdemo.domain.cars;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name = "car_park_log")
public class CarParLog {


    /**
     * CarId : 1
     * ParkInTime : 2018-11-24 08:39:20
     * ParkOutTime : 2018-11-24 08:39:28
     * Money : 2
     */

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @JSONField(name = "carId")
    private int id;
    @Column(name = "userId")
    @JsonIgnore
    private int userId;
    @Column(name = "ParkInTime")
    private Date ParkInTime;
    @Column(name = "ParkOutTime")
    private Date ParkOutTime;
    @Column(name = "Money")
    private int Money;


}
