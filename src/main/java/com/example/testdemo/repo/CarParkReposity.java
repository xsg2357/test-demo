package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarPark;
import com.example.testdemo.domain.cars.CarUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarParkReposity extends JpaRepository<CarPark,Long> {

    CarPark findCarParkByUserId(int userId);

    List<CarPark> findCarParksBySatusAndUserId(int satus,int userId);

}
