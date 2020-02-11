package com.example.testdemo.domain.cars;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "car_park_near")
public class CarParkNear {


    /**
     * ERRMSG : 成功
     * RESULT : S
     * img : img/002.png
     * longitude : 32.126599725
     * latitude : 118.9478159559
     * ROWS_DETAIL : {"name":"南京工业职业技术学院停车场 ","address":"南京栖霞路洋山北路 1 号 ","EmptySpace":30,"AllSpace":503,"distance":417,"open":1,"remarks":"停车场收 费标准由全市停车系统统一定价，最高 40 元/天。"}
     */

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @JsonIgnore
    private int id;
    @Column(name = "userId")
    @JsonIgnore
    private int userId;

    @Column(name = "img")
    private String img;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "latitude")
    private double latitude;

    /**
     * name : 南京工业职业技术学院停车场
     * address : 南京栖霞路洋山北路 1 号
     * EmptySpace : 30
     * AllSpace : 503
     * distance : 417
     * open : 1
     * remarks : 停车场收 费标准由全市停车系统统一定价，最高 40 元/天。
     */

    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "EmptySpace")
    private int EmptySpace;
    @Column(name = "AllSpace")
    private int AllSpace;
    @Column(name = "distance")
    private int distance;
    @Column(name = "open")
    private int open;
    @Column(name = "remarks")
    private String remarks;


}
