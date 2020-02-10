package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarUserReposity extends JpaRepository<CarUser,Long> {

    CarUser findCarUserByUserName(String userName);

}
