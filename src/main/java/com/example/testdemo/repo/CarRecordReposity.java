package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarModel;
import com.example.testdemo.domain.cars.CarRecordDetai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRecordReposity extends JpaRepository<CarRecordDetai,Long> {

    List<CarRecordDetai> findCarRecordDetaisByCarnumber(int carnumber);
}
