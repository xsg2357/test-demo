package com.example.testdemo.domain.cars;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name = "car_news")
public class CarNews {

    /**
     * id : 1
     * category : 1
     * title : 新 闻标题
     * content : 新闻内容
     * createtime : 2017-06-03 14:19:21
     */

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private int id;
    @Column(name = "category")
    private int category;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "createtime")
    private Date createtime;


}
