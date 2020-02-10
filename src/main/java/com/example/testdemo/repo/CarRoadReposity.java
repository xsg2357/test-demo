package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarETC;
import com.example.testdemo.domain.cars.CarRoad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRoadReposity extends JpaRepository<CarRoad,Long> {

    CarRoad findCarRoadByUserIdAndId(int userId,int id);

}
