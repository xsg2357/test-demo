package com.example.testdemo.domain.cars;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "car_pinfo")
public class CarInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @JsonIgnore
    private int id;

    /**
     * carnumber : 鲁 B10001
     * number  : 1
     *  pcardid  : 370101196101011001
     * buydata : 2016.5.1
     * carbrand : audi
     */

    @Column(name = "carnumber")
    private String carnumber;
    @Column(name = "number")
    private int number;
    @Column(name = "pcardid")
    private String pcardid;
    @Column(name = "buydata")
    private String buydata;
    @Column(name = "carbrand")
    private String carbrand;

    @Column(name = "userId")
    @JsonIgnore
    private int userId;



}
