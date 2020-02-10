package com.example.testdemo.repo;

import com.example.testdemo.domain.cars.CarModel;
import com.example.testdemo.repo.dao.CarModelDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CarModelReposity extends JpaRepository<CarModel,Long> {


    @Query(value="select p.Path  from car_info u right join car_route_detail p on  u.id = :Id",
            nativeQuery = true)
    List<CarModelDao>  findViewInfoById(@Param("Id") int Id);


}
