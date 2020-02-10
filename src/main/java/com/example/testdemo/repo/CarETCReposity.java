package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarETC;
import com.example.testdemo.domain.cars.CarLightSensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarETCReposity extends JpaRepository<CarETC,Long> {

    CarETC findCarETCByUserId(int userId);

}
