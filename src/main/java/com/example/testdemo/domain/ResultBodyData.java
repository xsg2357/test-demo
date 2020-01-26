package com.example.testdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Component
public class ResultBodyData<T>  implements Serializable {

    private int code;
    private String msg;
    private T data;
}
