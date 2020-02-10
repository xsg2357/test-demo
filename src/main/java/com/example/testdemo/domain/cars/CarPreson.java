package com.example.testdemo.domain.cars;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.Date;

@Data
@Entity(name = "car_preson")
public class CarPreson {


    /**
     * pname : 何颖升
     * pcardid : 370213196502141014
     * psex : 男
     * username : user14
     * ptel : 13804110014
     * pregistdate : 1990/6/3 14:19:21
     */
    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private int id;

    @Column(name = "pname")
    private String pname;
    @Column(name = "pcardid")
    private String pcardid;
    @Column(name = "psex")
    private String psex;
    @Column(name = "username")
    private String username;
    @Column(name = "ptel")
    private String ptel;
    @Column(name = "pregistdate")
    private Date pregistdate;


}
