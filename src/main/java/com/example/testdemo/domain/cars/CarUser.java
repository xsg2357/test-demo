package com.example.testdemo.domain.cars;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;


@Data
@Entity(name = "car_user")
public class CarUser {

    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private int id;
    @Column(name = "userName")
    private  String userName;
    @Column(name = "userPwd")
    private  String serPwd;

}
