package com.example.testdemo.controller.cars;

import com.alibaba.fastjson.JSONObject;
import com.example.testdemo.domain.ResultBodyData;
import com.example.testdemo.domain.cars.*;
import com.example.testdemo.exception.CustomException;
import com.example.testdemo.exception.ErrorInfo;
import com.example.testdemo.repo.*;
import com.example.testdemo.repo.dao.CarModelDao;
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
    @Autowired
    CarModelReposity carModelReposity;
    @Autowired
    CarModelsReposity carModelsReposity;
    @Autowired
    CarRecordReposity carRecordReposity;
    @Autowired
    CarTRafficReposity carTRafficReposity;
    @Autowired
    CarPatternReposity carPatternReposity;
    @Autowired
    CarUserReposity carUserReposity;
    @Autowired
    CarLSReposity carLSReposity;
    @Autowired
    CarSensorReposity carSensorReposity;
    @Autowired
    CarUserRoleReposity carUserRoleReposity;
    @Autowired
    CarPresonReposity carPresonReposity;
    @Autowired
    CarETCReposity carETCReposity;
    @Autowired
    CarETCLogReposity carETCLogReposity;
    @Autowired
    CarRoadReposity carRoadReposity;


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

    @PostMapping(value = "/action/GetSUserInfo.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<List<CarPreson>> GetSUserInfo(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        return new ResultBodyData<>(0, "oK", carPresonReposity.findAll());
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

    @PostMapping(value = "/action/GetCarPeccancy.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<List<CarRecordDetai>> carRecordReposity(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("carnumber")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        if (jsonParam.getIntValue("carnumber") == 0) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }
        return new ResultBodyData<>(0, "oK", carRecordReposity.findCarRecordDetaisByCarnumber(jsonParam.getIntValue("carnumber")));
    }

    @PostMapping(value = "/action/GetAllCarPeccancy.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<List<CarRecordDetai>> GetAllCarPeccancy(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        return new ResultBodyData<>(0, "oK", carRecordReposity.findAll());
    }

    @PostMapping(value = "/action/GetBusCapacity.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<Map<String, Object>> getBusCapacity(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("BusId")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        int busId = jsonParam.getIntValue("BusId");
        if (busId == 0) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }
        Map<String, Object> data = new HashMap<>();
        if (busId == 1) {
            data.put("BusCapacity", 10);
        } else {
            data.put("BusCapacity", 20);
        }
        return new ResultBodyData<>(0, "oK", data);
    }

    @PostMapping(value = "/action/SetSuggestion.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<String> setSuggestion(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("Title") || !jsonParam.containsKey("Content")
                || !jsonParam.containsKey("PhoneNumber")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }

        if (StringUtil.isNullOrEmpty(jsonParam.getString("Title")) ||
                StringUtil.isNullOrEmpty(jsonParam.getString("Content")) ||
                StringUtil.isNullOrEmpty(jsonParam.getString("PhoneNumber"))) {
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

    @PostMapping(value = "/action/GetCarPath.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<Map<String, Object>> getCarPath(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("CarId")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        int carId = jsonParam.getIntValue("CarId");
        if (carId == 0 || carId > 4) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }
        List<CarModelDao> viewInfo = carModelReposity.findViewInfoById(carId);
        if (viewInfo == null || viewInfo.size() == 0) {
            throw new CustomException(ErrorInfo.RESULT_EMPTY);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("Path", viewInfo.get(0).getPath());
        return new ResultBodyData<>(0, "oK", data);
    }

    @PostMapping(value = "/action/GetCarLocation.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<Map<String, Object>> getCarLocation(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("CarId")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        int carId = jsonParam.getIntValue("CarId");
        if (carId == 0 || carId > 4) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }
        CarModel alls = carModelsReposity.findCarModelById(carId);
        if (alls == null) {
            throw new CustomException(ErrorInfo.RESULT_EMPTY);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("CarLocationX", alls.getCarLocationX());
        data.put("CarLocationY", alls.getCarLocationY());
        return new ResultBodyData<>(0, "oK", data);
    }

    @PostMapping(value = "/action/GetCarSpeed.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<Map<String, Object>> getCarSpeed(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("CarId")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        int carId = jsonParam.getIntValue("CarId");
        if (carId == 0 || carId > 4) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }
        CarModel alls = carModelsReposity.findCarModelById(carId);
        if (alls == null) {
            throw new CustomException(ErrorInfo.RESULT_EMPTY);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("CarSpeed", alls.getCarSpeed());
        return new ResultBodyData<>(0, "oK", data);
    }


    @PostMapping(value = "/action/GetCarAccountBalance.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<Map<String, Object>> GetCarAccountBalance(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("CarId")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        int carId = jsonParam.getIntValue("CarId");
        if (carId == 0 || carId > 4) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }
        CarModel alls = carModelsReposity.findCarModelById(carId);
        if (alls == null) {
            throw new CustomException(ErrorInfo.RESULT_EMPTY);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("Balance", alls.getBalance());
        return new ResultBodyData<>(0, "oK", data);
    }

    @PostMapping(value = "/action/SetCarMove.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<String> SetCarMove(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("CarId") || !jsonParam.containsKey("CarAction")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        int carId = jsonParam.getIntValue("CarId");
        if (carId == 0 || carId > 4) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }

        CarModel alls = carModelsReposity.findCarModelById(carId);
        if (alls == null) {
            throw new CustomException(ErrorInfo.RESULT_EMPTY);
        }

        String carAction = jsonParam.getString("CarAction");
        if (carAction.equals("Start") || carAction.equals("Stop")) {
            alls.setCarAction(carAction);
        } else {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }

        carModelsReposity.save(alls);
        return new ResultBodyData<>(0, "oK", "");
    }


    @PostMapping(value = "/action/SetCarAccountRecharge.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<String> SetCarAccountRecharge(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("CarId") || !jsonParam.containsKey("Money")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        int carId = jsonParam.getIntValue("CarId");
        if (carId == 0 || carId > 4) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }

        CarModel alls = carModelsReposity.findCarModelById(carId);
        if (alls == null) {
            throw new CustomException(ErrorInfo.RESULT_EMPTY);
        }

        int money = jsonParam.getIntValue("Money");
        if (money != 0) {
            alls.setBalance(alls.getBalance() + money);
        } else {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }

        carModelsReposity.save(alls);
        return new ResultBodyData<>(0, "oK", "");
    }

    @PostMapping(value = "/action/SetCarSpeed.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<String> SetCarSpeed(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("CarId") || !jsonParam.containsKey("Speed")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        int carId = jsonParam.getIntValue("CarId");
        if (carId == 0 || carId > 4) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }

        CarModel alls = carModelsReposity.findCarModelById(carId);
        if (alls == null) {
            throw new CustomException(ErrorInfo.RESULT_EMPTY);
        }

        int speed = jsonParam.getIntValue("Speed");
        if (speed != 0) {
            alls.setCarSpeed(speed);
        } else {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }

        carModelsReposity.save(alls);
        return new ResultBodyData<>(0, "oK", "");
    }

    @PostMapping(value = "/action/SetCarPath.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<String> SetCarPath(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("CarId") || !jsonParam.containsKey("PathId")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        int carId = jsonParam.getIntValue("PathId");
        if (carId == 0 || carId > 4) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }

        CarModel alls = carModelsReposity.findCarModelById(carId);
        if (alls == null) {
            throw new CustomException(ErrorInfo.RESULT_EMPTY);
        }

        int PathId = jsonParam.getIntValue("PathId");
        if (PathId > 0 && PathId < 4) {
            alls.setPathId(PathId);
        } else {
            throw new CustomException(ErrorInfo.PATH_PARAMS_VALUES_ERROR);
        }

        carModelsReposity.save(alls);
        return new ResultBodyData<>(0, "oK", "");
    }


    @PostMapping(value = "/action/SetTrafficLightNowStatus.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<String> SetTrafficLightNowStatus(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("Status")
                || !jsonParam.containsKey("Time")
                || !jsonParam.containsKey("TrafficLightId")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        String status = jsonParam.getString("Status");
        String TrafficLightId = jsonParam.getString("TrafficLightId");
        if (StringUtil.isNullOrEmpty(status) || StringUtil.isNullOrEmpty(TrafficLightId)) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }
        CarTraffic alls = carTRafficReposity.findCarTrafficByTrafficLightId(TrafficLightId);
        if (alls == null) {
            throw new CustomException(ErrorInfo.RESULT_TRAFFIC_EMPTY);
        }
        int time = jsonParam.getIntValue("Time");
        alls.setStatus(status);
        switch (status) {
            case "red":
                alls.setRedTime(time);
                break;
            case "green":
                alls.setGreenTime(time);
                break;
            case "yellow":
                alls.setYellowTime(time);
                break;
            default:
                throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }
        carTRafficReposity.save(alls);
        return new ResultBodyData<>(0, "oK", "");
    }


    @PostMapping(value = "/action/GetTrafficLightNowStatus.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<Map<String, Object>> GetTrafficLightNowStatus(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("TrafficLightId")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        String TrafficLightId = jsonParam.getString("TrafficLightId");
        if (StringUtil.isNullOrEmpty(TrafficLightId)) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }
        CarTraffic alls = carTRafficReposity.findCarTrafficByTrafficLightId(TrafficLightId);
        if (alls == null) {
            throw new CustomException(ErrorInfo.RESULT_TRAFFIC_EMPTY);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("Status", alls.getStatus());
        switch (alls.getStatus()) {
            case "red":
                data.put("Time", alls.getRedTime());
                break;
            case "green":
                data.put("Time", alls.getGreenTime());
                break;
            case "yellow":
                data.put("Time", alls.getYellowTime());
                break;
        }

        return new ResultBodyData<>(0, "oK", data);
    }

    @PostMapping(value = "/action/SetTrafficLightConfig.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<String> SetTrafficLightConfig(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("GreenTime")
                || !jsonParam.containsKey("RedTime")
                || !jsonParam.containsKey("YellowTime")
                || !jsonParam.containsKey("TrafficLightId")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        int GreenTime = jsonParam.getIntValue("GreenTime");
        int RedTime = jsonParam.getIntValue("RedTime");
        int YellowTime = jsonParam.getIntValue("YellowTime");
        String TrafficLightId = jsonParam.getString("TrafficLightId");
        if (GreenTime == 0 || RedTime == 0 ||
                YellowTime == 0 || StringUtil.isNullOrEmpty(TrafficLightId)) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }
        CarTraffic alls = carTRafficReposity.findCarTrafficByTrafficLightId(TrafficLightId);
        if (alls == null) {
            throw new CustomException(ErrorInfo.RESULT_TRAFFIC_EMPTY);
        }
        alls.setRedTime(RedTime);
        alls.setGreenTime(GreenTime);
        alls.setYellowTime(YellowTime);
        carTRafficReposity.save(alls);
        return new ResultBodyData<>(0, "oK", "");
    }

    @PostMapping(value = "/action/GetTrafficLightConfigAction.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<Map<String, Object>> GetTrafficLightConfigAction(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("TrafficLightId")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        String TrafficLightId = jsonParam.getString("TrafficLightId");
        if (StringUtil.isNullOrEmpty(TrafficLightId)) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }
        CarTraffic alls = carTRafficReposity.findCarTrafficByTrafficLightId(TrafficLightId);
        if (alls == null) {
            throw new CustomException(ErrorInfo.RESULT_TRAFFIC_EMPTY);
        }

        Map<String, Object> data = new HashMap<>();
//        data.put("Status", alls.getStatus());
        data.put("RedTime", alls.getRedTime());
        data.put("GreenTime", alls.getGreenTime());
        data.put("YellowTime", alls.getYellowTime());

        return new ResultBodyData<>(0, "oK", data);
    }

    @PostMapping(value = "/action/SetRoadLightControlMode.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<String> SetRoadLightControlMode(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("ControlMode")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        String ControlMode = jsonParam.getString("ControlMode");
        if (!StringUtil.isNullOrEmpty(ControlMode) && ControlMode.equals("Auto") || ControlMode.equals("Manual")) {
            CarPattern alls = carPatternReposity.findCarPatternByControlMode(ControlMode);
            if (alls == null) {
                throw new CustomException(ErrorInfo.RESULT_EMPTY);
            }
            alls.setControlMode(ControlMode);
            carPatternReposity.save(alls);
        } else {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }


        return new ResultBodyData<>(0, "oK", "");
    }

    @PostMapping(value = "/action/SetRoadLightStatusAction.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<String> SetRoadLightStatusAction(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("RoadLightId") || !jsonParam.containsKey("Action")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        int roadLightId = jsonParam.getIntValue("RoadLightId");
        if (roadLightId <= 0 || roadLightId > 3) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }
        String ControlMode = jsonParam.getString("Action");
        CarPattern alls = carPatternReposity.findCarPatternById(roadLightId);
        if (!StringUtil.isNullOrEmpty(ControlMode) && ControlMode.equals("Close") || ControlMode.equals("Open")) {
            if (alls == null) {
                throw new CustomException(ErrorInfo.RESULT_EMPTY);
            }
            alls.setAction(ControlMode);
            carPatternReposity.save(alls);
        } else {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }


        return new ResultBodyData<>(0, "oK", "");
    }

    @PostMapping(value = "/action/GetRoadLightStatus.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<Map<String, Object>> GetRoadLightStatus(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("RoadLightId")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        int roadLightId = jsonParam.getIntValue("RoadLightId");
        if (roadLightId <= 0 || roadLightId > 3) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }
        String ControlMode = jsonParam.getString("Action");
        CarPattern alls = carPatternReposity.findCarPatternById(roadLightId);
        if (alls == null) {
            throw new CustomException(ErrorInfo.RESULT_EMPTY);
        }
        alls.setControlMode(ControlMode);
        Map<String, Object> data = new HashMap<>();
        data.put("Status", alls.getAction());

        return new ResultBodyData<>(0, "oK", data);
    }

    @PostMapping(value = "/action/SetLightSenseValve.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<String> SetLightSenseValve(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("Down") || !jsonParam.containsKey("Up")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }

        String UserName = jsonParam.getString("UserName");
        if (StringUtil.isNullOrEmpty(UserName)) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }
        CarUser carUser = carUserReposity.findCarUserByUserName(UserName);
        if (carUser == null) {
            throw new CustomException(ErrorInfo.USER_NOT_EXIST);
        }
        CarLightSensor carLightSensor = carLSReposity.findCarLightSensorByUserId(carUser.getId());
        if (carLightSensor == null) {
            throw new CustomException(ErrorInfo.RESULT_EMPTY);
        }
        carLightSensor.setDown(jsonParam.getIntValue("Down"));
        carLightSensor.setUp(jsonParam.getIntValue("Up"));
        carLSReposity.save(carLightSensor);

        return new ResultBodyData<>(0, "oK", "");
    }

    @PostMapping(value = "/action/GetLightSenseValve.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<Map<String, Object>> GetLightSenseValve(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }

        String UserName = jsonParam.getString("UserName");
        if (StringUtil.isNullOrEmpty(UserName)) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }
        CarUser carUser = carUserReposity.findCarUserByUserName(UserName);
        if (carUser == null) {
            throw new CustomException(ErrorInfo.USER_NOT_EXIST);
        }
        CarLightSensor carLightSensor = carLSReposity.findCarLightSensorByUserId(carUser.getId());
        if (carLightSensor == null) {
            throw new CustomException(ErrorInfo.RESULT_EMPTY);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("Down", carLightSensor.getDown());
        data.put("Up", carLightSensor.getUp());

        return new ResultBodyData<>(0, "oK", data);
    }


    @PostMapping(value = "/action/GetAllSense.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<CarSensor> GetAllSense(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }

        String UserName = jsonParam.getString("UserName");
        if (StringUtil.isNullOrEmpty(UserName)) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }
        CarUser carUser = carUserReposity.findCarUserByUserName(UserName);
        if (carUser == null) {
            throw new CustomException(ErrorInfo.USER_NOT_EXIST);
        }
        CarSensor carLightSensor = carSensorReposity.findCarSensorByUserId(carUser.getId());
        if (carLightSensor == null) {
            throw new CustomException(ErrorInfo.RESULT_EMPTY);
        }

        return new ResultBodyData<>(0, "oK", carLightSensor);
    }

    @PostMapping(value = "/action/GetSenseByName.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<Map<String, Object>> GetSenseByName(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("SenseName")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }

        String SenseName = jsonParam.getString("SenseName");
        String UserName = jsonParam.getString("UserName");
        if (StringUtil.isNullOrEmpty(UserName) || StringUtil.isNullOrEmpty(SenseName)) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }
        CarUser carUser = carUserReposity.findCarUserByUserName(UserName);
        if (carUser == null) {
            throw new CustomException(ErrorInfo.USER_NOT_EXIST);
        }
        CarSensor carSensor = carSensorReposity.findCarSensorByUserId(carUser.getId());
        if (carSensor == null) {
            throw new CustomException(ErrorInfo.RESULT_EMPTY);
        }
        Map<String, Object> data = new HashMap<>();
        switch (SenseName) {
            case "temperature":
                data.put("temperature", carSensor.getTemperature());
                break;
            case "humidity":
                data.put("humidity", carSensor.getHumidity());
                break;
            case "co2":
                data.put("co2", carSensor.getCo2());
                break;
            case "LightIntensity":
                data.put("LightIntensity", carSensor.getLightIntensity());
                break;
            case "pm2.5":
                data.put("pm2.5", carSensor.get_Pm255());
                break;
            default:
                throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }

        return new ResultBodyData<>(0, "oK", data);
    }

    @PostMapping(value = "/action/user_login.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<Map<String, Object>> user_login(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("UserPwd")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }

        String UserName = jsonParam.getString("UserName");
        String UserPwd = jsonParam.getString("UserPwd");
        if (StringUtil.isNullOrEmpty(UserName) || StringUtil.isNullOrEmpty(UserPwd)) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }

        CarUser carUser = carUserReposity.findCarUserByUserName(UserName);
        if (carUser == null) {
            throw new CustomException(ErrorInfo.USER_NOT_EXIST);
        }

        if (carUser.getSerPwd().equals(UserPwd)) {
            throw new CustomException(ErrorInfo.USER_PWD_NOT_EXIST);
        }

        CarUserRole carSensor = carUserRoleReposity.findCarUserRoleByUserId(carUser.getId());
        if (carSensor == null) {
            throw new CustomException(ErrorInfo.RESULT_EMPTY);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("UserRole", carSensor.getUserRole());


        return new ResultBodyData<>(0, "oK", data);
    }


    @PostMapping(value = "/action/GetEtcListReport.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<List<CarETCLog>> GetEtcListReport(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }

        String UserName = jsonParam.getString("UserName");
        if (StringUtil.isNullOrEmpty(UserName)) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }

        CarUser carUser = carUserReposity.findCarUserByUserName(UserName);
        if (carUser == null) {
            throw new CustomException(ErrorInfo.USER_NOT_EXIST);
        }

        List<CarETCLog> carETCLogsByUserId = carETCLogReposity.findCarETCLogsByUserId(carUser.getId());


        return new ResultBodyData<>(0, "oK", carETCLogsByUserId);
    }

    @PostMapping(value = "/action/GetRoadStatus.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<Map<String,Object>> GetRoadStatus(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("RoadId")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }

        String UserName = jsonParam.getString("UserName");
        int RoadId = jsonParam.getIntValue("RoadId");
        if (StringUtil.isNullOrEmpty(UserName) || RoadId == 0) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }

        CarUser carUser = carUserReposity.findCarUserByUserName(UserName);
        if (carUser == null) {
            throw new CustomException(ErrorInfo.USER_NOT_EXIST);
        }

        CarRoad car = carRoadReposity.findCarRoadByUserIdAndId(carUser.getId(),RoadId);
        Map<String, Object> data = new HashMap<>();
        data.put("Status", car.getStatus());

        return new ResultBodyData<>(0, "oK", data);
    }

    @PostMapping(value = "/action/GetEtcRate.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<Map<String, Object>> GetEtcRate(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }

        String UserName = jsonParam.getString("UserName");
        if (StringUtil.isNullOrEmpty(UserName)) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }

        CarUser carUser = carUserReposity.findCarUserByUserName(UserName);
        if (carUser == null) {
            throw new CustomException(ErrorInfo.USER_NOT_EXIST);
        }

        CarETC carSensor = carETCReposity.findCarETCByUserId(carUser.getId());
        if (carSensor == null) {
            throw new CustomException(ErrorInfo.RESULT_EMPTY);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("Money", carSensor.getMoney());

        return new ResultBodyData<>(0, "oK", data);
    }


    @PostMapping(value = "/action/SetEtcRate.do", produces = "application/json;charset=UTF-8")
    public ResultBodyData<String> SetEtcRate(@RequestBody JSONObject jsonParam) {
        if (!jsonParam.containsKey("UserName") || !jsonParam.containsKey("RateType") || !jsonParam.containsKey("Money")) {
            throw new CustomException(ErrorInfo.PARAMS_ERROR);
        }

        String UserName = jsonParam.getString("UserName");
        String RateType = jsonParam.getString("RateType");
        int Money = jsonParam.getIntValue("Money");
        if (StringUtil.isNullOrEmpty(UserName) || StringUtil.isNullOrEmpty(RateType)) {
            throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
        }

        CarUser carUser = carUserReposity.findCarUserByUserName(UserName);
        if (carUser == null) {
            throw new CustomException(ErrorInfo.USER_NOT_EXIST);
        }

        CarETC carSensor = carETCReposity.findCarETCByUserId(carUser.getId());
        if (carSensor == null) {
            carSensor = new CarETC();
            if (RateType.equals("Hour") || RateType.equals("Count")) {
                carSensor.setRateType(RateType);
            } else {
                throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
            }
            carSensor.setUserId(carUser.getId());
            carSensor.setMoney(Money);
        } else {
            carSensor = new CarETC();
            if (RateType.equals("Hour") || RateType.equals("Count")) {
                carSensor.setMoney(Money);
            } else {
                throw new CustomException(ErrorInfo.PARAMS_VALUES_ERROR);
            }
        }
        carETCReposity.save(carSensor);

        return new ResultBodyData<>(0, "oK", "");
    }

}
