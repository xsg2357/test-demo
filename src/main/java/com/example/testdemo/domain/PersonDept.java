package com.example.testdemo.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "person_dept")
public class PersonDept implements Serializable {
    @Column(name = "p_id")
    private Integer pId;

    @Column(name = "dept_id")
    private Integer deptId;

    private static final long serialVersionUID = 1L;
}