package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarInfoReposity extends JpaRepository<CarInfo,Long> {


    List<CarInfo> findCarInfosByUserId(int userId);

}
