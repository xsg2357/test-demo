package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelsReposity extends JpaRepository<CarModel,Long> {

    CarModel findCarModelById(int Id);
}
