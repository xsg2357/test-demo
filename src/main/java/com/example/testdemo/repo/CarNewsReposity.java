package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarNews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarNewsReposity extends JpaRepository<CarNews,Long> {

    List<CarNews> findAllByCategory(int Category);
}
