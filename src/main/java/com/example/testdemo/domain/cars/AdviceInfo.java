package com.example.testdemo.domain.cars;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.Date;

@Data
@Entity(name = "car_advice")
public class AdviceInfo {
    /**
     * Id : 1
     * Title : 建议标题
     * Content : 建议内容
     * PhoneNumber : 13811112222
     * SendDatetime : 2017-6-3 14:19:21
     * Flag : 0
     * ReplyContent : null
     * ReplyDatetime : null
     */
    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private int Id;
    @Column(name = "Title")
    private String Title;
    @Column(name = "Content")
    private String Content;
    @Column(name = "PhoneNumber")
    private String PhoneNumber;
    @Column(name = "SendDatetime")
    private String SendDatetime;
    @Column(name = "Flag")
    private int Flag;
    @Column(name = "ReplyContent")
    private String ReplyContent;
    @Column(name = "ReplyDatetime")
    private Date ReplyDatetime;


}
