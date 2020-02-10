package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarUser;
import com.example.testdemo.domain.cars.CarUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarUserRoleReposity extends JpaRepository<CarUserRole,Long> {

    CarUserRole findCarUserRoleByUserId(int  userId);

}
