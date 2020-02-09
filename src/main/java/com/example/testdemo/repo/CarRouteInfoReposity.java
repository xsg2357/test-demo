package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarRouteInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRouteInfoReposity extends JpaRepository<CarRouteInfo,Long> {

}
