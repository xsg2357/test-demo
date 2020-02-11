package com.example.testdemo.domain.cars;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "car_bus")
public class CarBus {

    /**
     * Id : 1
     * PhoneNumber : 13811112222
     * StartSite : 大庙村
     * EndSite : 热河路
     * BusDate : 2018-04-03,2018-04-16,2018-04- 17
     * Ticket : 8
     * Flag : 0
     */

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @JSONField(name = "ID")
    private int id;
    @Column(name = "userId")
    @JsonIgnore
    private int userId;
    @Column(name = "PhoneNumber")
    private String PhoneNumber;
    @Column(name = "StartSite")
    private String StartSite;
    @Column(name = "EndSite")
    private String EndSite;
    @Column(name = "BusDate")
    private String BusDate;
    @Column(name = "Ticket")
    private int Ticket;
    @Column(name = "Flag")
    private int Flag;

}
