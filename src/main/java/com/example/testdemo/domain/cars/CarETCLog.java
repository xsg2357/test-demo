package com.example.testdemo.domain.cars;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.Date;

@Data
@Entity(name = "car_etc_log")
public class CarETCLog {


    @Column(name = "userId")
    private int userId;
    /**
     * CarId : 2
     * EtcInTime : 2018-11-24 08:45:42
     * EtcOutTime : 2018-11-24 08:45:56
     * Money : 5
     */

    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private int carId;
    @Column(name = "EtcInTime")
    private Date EtcInTime;
    @Column(name = "EtcOutTime")
    private Date EtcOutTime;
    @Column(name = "Money")
    private int Money;


}
