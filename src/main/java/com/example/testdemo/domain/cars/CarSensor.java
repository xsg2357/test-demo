package com.example.testdemo.domain.cars;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Data
@Entity(name = "car_sensor")
public class CarSensor {


    /**
     * RESULT : S
     * ERRMSG : 成功
     * pm2.5 : 8
     * co2 : 5919
     * LightIntensity : 1711
     * humidity : 44
     * temperature : 28
     */
    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @JsonIgnore
    private int id;

    @Column(name = "_Pm255")
    @JSONField(name="pm2")
    private int _Pm255; // FIXME check this code
    @Column(name = "co2")
    private int co2;
    @Column(name = "LightIntensity")
    private int LightIntensity;
    @Column(name = "humidity")
    private int humidity;
    @Column(name = "temperature")
    private int temperature;
    @Column(name = "userId")
    private int userId;

}
