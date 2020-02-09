package com.example.testdemo.domain.cars;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "car_trade_info")
public class TradeInfo {

    /**
     * id : 1
     * name : 故宫
     * ticket : 80
     * img : img/002.png
     * info : 北京故宫是中国明清两代的皇家宫殿，旧 称为紫禁城，位于北京中轴线的中心，是中国古代宫廷建筑之精华。北京故宫以三大殿为 中心，占地面积 72 万平方米，建筑面积约 15 万平方米，有大小宫殿七十多座，房屋九千 余间。是世界上现存规模最大、保存最为完整的木质结构古建筑之一。
     * tel : 010- 88888888
     * rating : 5
     */

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "ticket")
    private int ticket;
    @Column(name = "img")
    private String img;
    @Column(name = "info")
    private String info;
    @Column(name = "tel")
    private String tel;
    @Column(name = "rating")
    private int rating;


}
