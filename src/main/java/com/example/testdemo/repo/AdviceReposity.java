package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.AdviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdviceReposity extends JpaRepository<AdviceInfo,Long> {

}
