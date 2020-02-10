package com.example.testdemo.domain.cars;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name = "car_record_detail")
public class CarRecordDetai {


    /**
     * carnumber : 鲁 B10001
     * pcode : 1001A
     * paddr : 学院路
     * pdatetime :  2016/5/21 8:19:21
     */

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private  int id;
    @Column(name = "car_id")
    private int carnumber;
    @Column(name = "pcode")
    private String pcode;
    @Column(name = "paddr")
    private String paddr;
    @Column(name = "pdatetime")
    private Date pdatetime;


}
