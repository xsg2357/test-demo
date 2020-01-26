package com.example.testdemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "address_info")
public class AddressInfo implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "area")
    private String area;

    @Column(name = "address")
    private String address;

    @Column(name = "type")
    private int type;

    @JsonIgnore
    @Column(name = "isDelete")
    private int isDelete;

    @Column(name = "lat")
    private double lat;

    @Column(name = "lng")
    private double lng;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "userId")
    private int userId;


    private static final long serialVersionUID = 1L;
}