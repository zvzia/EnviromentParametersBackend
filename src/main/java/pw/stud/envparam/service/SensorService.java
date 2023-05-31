package pw.stud.envparam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pw.stud.envparam.dao.*;
import pw.stud.envparam.model.DataFromSensorRequest;

import java.util.Date;


@Service
public class SensorService {
    @Autowired
    SurroundingConditionRepo surroundingConditionRepo;


    public void sendData(@RequestBody DataFromSensorRequest dataFromSensorRequest){

        SurroundingConditionEn condition = new SurroundingConditionEn();
        condition.setSensorId(dataFromSensorRequest.getSensorId());
        condition.setTemperature((double) dataFromSensorRequest.getTemperature());
        condition.setHumidity(dataFromSensorRequest.getHumidity());
        Date date = new Date();
        condition.setDate(new java.sql.Timestamp(date.getTime()));
        condition.setBatteryLevel(dataFromSensorRequest.getBatteryLevel());
        surroundingConditionRepo.save(condition);
    }
}
