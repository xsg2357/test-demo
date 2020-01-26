package com.example.testdemo.domain;

import lombok.Data;

import java.util.List;

@Data
public class PageEntity<T> {

    private List<T> content;
    private  int count;
    private  int  number;

}
