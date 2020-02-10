package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarPreson;
import com.example.testdemo.domain.cars.CarRecordDetai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarPresonReposity extends JpaRepository<CarPreson,Long> {

}
