package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarETCLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarETCLogReposity extends JpaRepository<CarETCLog,Long> {

    List<CarETCLog> findCarETCLogsByUserId(int userId);

}
