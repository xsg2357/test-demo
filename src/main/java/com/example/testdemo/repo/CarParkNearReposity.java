package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarParkNear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarParkNearReposity extends JpaRepository<CarParkNear,Long> {


    CarParkNear findCarParkNearByUserId(int userId);

}
