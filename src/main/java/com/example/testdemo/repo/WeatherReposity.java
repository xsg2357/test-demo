package com.example.testdemo.repo;


import com.example.testdemo.domain.cars.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherReposity extends JpaRepository<Weather,Long> {

}
