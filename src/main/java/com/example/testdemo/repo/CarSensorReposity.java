package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarSensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarSensorReposity extends JpaRepository<CarSensor,Long> {

    CarSensor findCarSensorByUserId(int userId);

}
