package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarModel;
import com.example.testdemo.domain.cars.CarPattern;
import com.example.testdemo.repo.dao.CarModelDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

public interface CarPatternReposity extends JpaRepository<CarPattern,Long> {

    CarPattern findCarPatternByControlMode(String cl);
    CarPattern findCarPatternById(int id);

}
