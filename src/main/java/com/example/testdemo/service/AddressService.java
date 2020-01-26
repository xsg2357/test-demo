package com.example.testdemo.service;


import com.example.testdemo.domain.AddressInfo;
import com.example.testdemo.domain.PageEntity;

public interface AddressService {

    // 排序分页
    PageEntity<AddressInfo> getPageSort(Integer pageNum, Integer pageLimit);

    long sumCount();
}
