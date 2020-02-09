package com.example.testdemo.domain.cars;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name = "car_route_ad")
public class CarRouteAd {

    /**
     * Id : 1
     * Title : 公告标题
     * Content : 公告内容
     * CreateTime : 2017-06-0314:19:21
     */

    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private int Id;
    @Column(name = "Title")
    private String Title;
    @Column(name = "Content")
    private String Content;
    @Column(name = "CreateTime")
    private Date CreateTime;


}
