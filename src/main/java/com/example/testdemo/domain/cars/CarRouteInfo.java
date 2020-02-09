package com.example.testdemo.domain.cars;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;
import java.util.List;

@Data
@Entity(name = "car_route_info")
public class CarRouteInfo {

    /**
     * name : 1 号线
     * namenumber : G18
     * sites : ["双岛出入口","北海出入口","酒馆出入口","牟平东出 入口","养马岛出入口","轸格庄出入口","莱山出入口","杜家疃出入口","崇义出入口"," 东厅出入口","古现出入口","蓬莱出入口","蓬莱西出入口","黄城出入口","龙口出入口 ","招远出入口","朱桥出入口","莱州东出入口","莱州出入口","沙河出入口","灰埠出入 口","新河出入口","下营出入口","昌邑出入口","潍坊北出入口","寿光东出入口","寿光 北出入口","寿光西出入口","李庄出入口","东营出入口","东营北出入口","垦利北出入 口","陈庄出入口","利津出入口","沾化东出入口","沾化西出入口","滨州港出入口","无 棣出入口","鲁北出入口"]
     */
    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private  int  id;
    @Column(name = "name")
    private String name;
    @Column(name = "namenumber")
    private String namenumber;
    @Column(name = "site")
    @JsonIgnore
    private String site;

    @Transient
    private List<String> sites;



}
