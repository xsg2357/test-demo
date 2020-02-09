package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarRouteAd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRouteAdReposity extends JpaRepository<CarRouteAd,Long> {

}
