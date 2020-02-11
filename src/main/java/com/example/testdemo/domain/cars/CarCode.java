package com.example.testdemo.domain.cars;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "car_code")
public class CarCode {


    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @JsonIgnore
    private int id;

    /**
     * pcode : 1001A
     * pmoney : 1000
     * pscore : 0
     * premarks : A 驾驶拼装的非汽车类机动车上道路行驶的
     */

    @Column(name = "pcode")
    private String pcode;
    @Column(name = "pmoney")
    private int pmoney;
    @Column(name = "pscore")
    private int pscore;
    @Column(name = "premarks")
    private String premarks;

    @Column(name = "userId")
    @JsonIgnore
    private int userId;

}
