package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarLightSensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarLSReposity extends JpaRepository<CarLightSensor,Long> {

    CarLightSensor findCarLightSensorByUserId(int userId);

}
