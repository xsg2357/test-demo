package com.example.testdemo.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "token_info")
public class TokenInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "token")
    private String token;

    @Column(name = "updateTime")
    private String updateTime;

    @Column(name = "arriveTime")
    private String arriveTime;

    @Column(name = "isSingleClient")
    private Integer isSingleClient;

    @Column(name = "isClient")
    private Integer isClient;

    @Column(name = "set_time")
    private long setTime;

    @Column(name = "userId")
    private Integer userId;


}
