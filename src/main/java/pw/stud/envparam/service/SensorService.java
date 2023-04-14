package pw.stud.envparam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pw.stud.envparam.dao.*;
import pw.stud.envparam.model.DataFromSensorRequest;
import pw.stud.envparam.model.SurroundingCondition;

import java.util.Calendar;
import java.util.Date;


@Service
public class SensorService {
    @Autowired
    SurroundingConditionRepo surroundingConditionRepo;
    @Autowired
    BatteryLevelRepo batteryLevelRepo;


    public void sendData(@RequestBody DataFromSensorRequest dataFromSensorRequest){

        SurroundingConditionEn condition = new SurroundingConditionEn();
        condition.setSensorId(dataFromSensorRequest.getSensorId());
        condition.setTemperature((double) dataFromSensorRequest.getTemperature());
        condition.setHumidity(dataFromSensorRequest.getHumidity());
        Date date = new Date();
        condition.setDate(new java.sql.Timestamp(date.getTime()));
        surroundingConditionRepo.save(condition);

        BatteryLevelEn batteryLevelEn = new BatteryLevelEn();
        batteryLevelEn.setSensorId(dataFromSensorRequest.getSensorId());
        batteryLevelEn.setBatteryLevel(dataFromSensorRequest.getBatteryLevel());
        batteryLevelEn.setDate(new java.sql.Timestamp(date.getTime()));
        batteryLevelRepo.save(batteryLevelEn);
    }
}
