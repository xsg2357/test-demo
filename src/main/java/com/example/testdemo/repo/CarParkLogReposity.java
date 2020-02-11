package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarParLog;
import com.example.testdemo.domain.cars.CarPark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarParkLogReposity extends JpaRepository<CarParLog,Long> {


    List<CarParLog> findCarParLogsByUserId( int userId);

}
