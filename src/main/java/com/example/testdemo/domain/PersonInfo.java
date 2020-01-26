package com.example.testdemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity(name = "user_info")
public class PersonInfo implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "`name`")
    private String name;

    @Column(name = "phone")
    private String phone;

    @JsonIgnore
    @Column(name = "pwd")
    private String pwd;

    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private int gender;

    @Column(name = "age")
    private int age;

    @Column(name = "educational")
    private String educational;

    @Column(name = "deptId")
    private int deptid;

    @Column(name = "isdelete")
    private int isdelete;

    private static final long serialVersionUID = 1L;
}