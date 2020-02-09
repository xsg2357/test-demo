package com.example.testdemo.repo;


import com.example.testdemo.domain.cars.TradeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeInfoReposity extends JpaRepository<TradeInfo,Long> {

}
