package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarBusStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarBusStationReposity extends JpaRepository<CarBusStation,Long> {


    List<CarBusStation> findCarBusStationsByUserId(int userId);

}
