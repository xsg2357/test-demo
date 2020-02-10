package com.example.testdemo.domain.cars;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;


@Data
@Entity(name = "car_user_role")
public class CarUserRole {

    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private int id;
    @Column(name = "userRole")
    private  String userRole;
    @Column(name = "userPwd")
    private  String serPwd;
    @Column(name = "userId")
    private int userId;

}
