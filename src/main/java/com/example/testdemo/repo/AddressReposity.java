package com.example.testdemo.repo;

import com.example.testdemo.domain.AddressInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressReposity extends JpaRepository<AddressInfo,Long> {

    Page<AddressInfo> findAllByUserIdAndIsDelete(int   userId,int isDelete, Pageable pageable);

    AddressInfo findAddressInfoById(int id);

}
