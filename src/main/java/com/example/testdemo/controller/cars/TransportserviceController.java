package com.example.testdemo.controller.cars;

import com.alibaba.fastjson.JSONObject;
import com.example.testdemo.domain.ResultBodyData;
import com.example.testdemo.domain.cars.*;
import com.example.testdemo.exception.CustomException;
import com.example.testdemo.exception.ErrorInfo;
import com.example.testdemo.repo.*;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/transportservice")
@RestController
public class TransportserviceController {

    @Autowired
    CarNewsReposity carNewsReposity;
    @Autowired
    TradeInfoReposity tradeInfoReposity;
    @Autowired
    CarRouteAdReposity carRouteAdReposity;
    @Autowired
    CarRouteInfoReposity carRouteInfoReposity;
    @Autowired
    AdviceReposity adviceReposity;
    @Autowired
    WeatherReposity weatherReposity;


    @PostMapping(value = "/action/GetNewsInfo.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<List<CarNews>> newsList(@RequestBody JSONObject jsonParam) {
        System.out.println(jsonParam.toJSONString());
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("Category")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        int category = jsonParam.getIntValue("Category");
        if (category == 0) {
            return new ResultBodyData<>(0, "oK", carNewsReposity.findAll());
        } else {
            return new ResultBodyData<>(0, "oK", carNewsReposity.findAllByCategory(category));
        }
    }


    @PostMapping(value = "/action/GetSpotInfo.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<List<TradeInfo>> spotInfo(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        return new ResultBodyData<>(0, "oK", tradeInfoReposity.findAll());
    }

    @PostMapping(value = "/action/GetMotorwayAnnouncement.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<List<CarRouteAd>> motorwayAnnouncement(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        return new ResultBodyData<>(0, "oK", carRouteAdReposity.findAll());
    }

    @PostMapping(value = "/action/GetMotorwayInfo.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<List<CarRouteInfo>> motorwayInfo(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        List<CarRouteInfo> all = carRouteInfoReposity.findAll();
        for (CarRouteInfo carRouteInfo : all) {
            String[] split = carRouteInfo.getSite().split(",");
            carRouteInfo.setSites(Arrays.asList(split));
        }
        return new ResultBodyData<>(0, "oK", all);
    }

    @PostMapping(value = "/action/GetAllSuggestion.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<List<AdviceInfo>> allSuggestion(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        return new ResultBodyData<>(0, "oK", adviceReposity.findAll());
    }

    @PostMapping(value = "/action/GetBusCapacity.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<Map<String,Object>> getBusCapacity(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("BusId")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        int busId = jsonParam.getIntValue("BusId");
        if (busId == 0){
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }
        Map<String,Object> data = new HashMap<>();
        if (busId ==1){
            data.put("BusCapacity",10);
        }else{
            data.put("BusCapacity",20);
        }
        return new ResultBodyData<>(0, "oK", data);
    }

    @PostMapping(value = "/action/SetSuggestion.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<String> setSuggestion(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") ||!jsonParam.containsKey("Title") ||!jsonParam.containsKey("Content")
                ||!jsonParam.containsKey("PhoneNumber") ) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }

        if (StringUtil.isNullOrEmpty(jsonParam.getString("Title"))||
                StringUtil.isNullOrEmpty(jsonParam.getString("Content"))||
                StringUtil.isNullOrEmpty(jsonParam.getString("PhoneNumber"))){
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }

        AdviceInfo adviceInfo = new AdviceInfo();
        adviceInfo.setContent(jsonParam.getString("Content"));
        adviceInfo.setPhoneNumber(jsonParam.getString("PhoneNumber"));
        adviceInfo.setTitle(jsonParam.getString("Title"));

        adviceReposity.save(adviceInfo);


        return new ResultBodyData<>(0, "oK", "");
    }

    @PostMapping(value = "/action/GetWeather.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<List<Weather>> getWeather(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        return new ResultBodyData<>(0, "oK", weatherReposity.findAll());
    }

}
