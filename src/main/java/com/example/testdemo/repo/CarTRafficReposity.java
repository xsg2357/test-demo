package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarNews;
import com.example.testdemo.domain.cars.CarTraffic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarTRafficReposity extends JpaRepository<CarTraffic,Long> {

    CarTraffic findCarTrafficByTrafficLightId(String TrafficLightId);
}
