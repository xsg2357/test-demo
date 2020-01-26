package com.example.testdemo.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "dept")
public class Dept implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "dept_name")
    private String deptName;

    private static final long serialVersionUID = 1L;
}