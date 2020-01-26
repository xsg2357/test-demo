package com.example.testdemo.domain;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "person_info")
public class AddressInfo implements Serializable {
    private Integer id;

    private String name;

    private String phone;

    private String address;

    private static final long serialVersionUID = 1L;
}