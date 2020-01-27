package com.example.testdemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "address_info")
public class AddressInfo implements Serializable {

    @ApiModelProperty(hidden=true)
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

    @ApiModelProperty(hidden=true)
    @JsonIgnore
    @Column(name = "isDelete")
    private int isDelete;

    @Column(name = "lat")
    private double lat;

    @Column(name = "lng")
    private double lng;

    @ApiModelProperty(hidden=true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ApiModelProperty(hidden=true)
    @Column(name = "userId")
    private int userId;


    private static final long serialVersionUID = 1L;
}