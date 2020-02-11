package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarCode;
import com.example.testdemo.domain.cars.CarInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarCodeReposity extends JpaRepository<CarCode,Long> {


    List<CarCode> findCarCodesByUserId(int userId);

}
