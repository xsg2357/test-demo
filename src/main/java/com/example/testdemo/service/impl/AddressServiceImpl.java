package com.example.testdemo.service.impl;

import com.example.testdemo.domain.AddressInfo;
import com.example.testdemo.domain.PageEntity;
import com.example.testdemo.repo.AddressReposity;
import com.example.testdemo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressReposity addressReposity;

    @Override
    public PageEntity<AddressInfo> getPageSort(int  userId,Integer pageNum, Integer pageLimit) {
        List<Sort.Order> list = new ArrayList<>();
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "createTime");
        list.add(order1);
        Sort sort = Sort.by(list);
        Pageable pageable = PageRequest.of(pageNum - 1, pageLimit, sort);
        Page<AddressInfo> all = addressReposity.findAllByUserId(userId,pageable);
        List<AddressInfo> content = all.getContent();

        PageEntity<AddressInfo> pageEntity = new PageEntity<>();
        pageEntity.setContent(content);
        pageEntity.setCount(all.getTotalPages());
        pageEntity.setNumber( all.getNumber());

        return  pageEntity;
    }

    @Override
    public long sumCount() {
        return addressReposity.count();
    }
}
