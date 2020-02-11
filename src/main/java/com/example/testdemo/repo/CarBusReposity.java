package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarBus;
import com.example.testdemo.domain.cars.CarPark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarBusReposity extends JpaRepository<CarBus,Long> {


    List<CarBus> findCarBusesByUserId(int userId);
    CarBus findCarBusByUserId(int userId);

}
