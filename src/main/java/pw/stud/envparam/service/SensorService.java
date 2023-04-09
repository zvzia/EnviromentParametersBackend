package pw.stud.envparam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import pw.stud.envparam.dao.SurroundingConditionEn;
import pw.stud.envparam.dao.SurroundingConditionRepo;
import pw.stud.envparam.model.DataFromSensorRequest;
import pw.stud.envparam.model.SurroundingCondition;

import java.util.Date;

public class SensorService {

    @Autowired
    SurroundingConditionRepo surroundingConditionRepo;
    public void sendData(@RequestBody DataFromSensorRequest dataFromSensorRequest){
        SurroundingConditionEn condition = new SurroundingConditionEn();
        condition.setSensorId(dataFromSensorRequest.getSensorId());
        condition.setTemperature((double) dataFromSensorRequest.getTemperature());
        condition.setHumidity(dataFromSensorRequest.getHumidity());
        Date date = new Date();
        condition.setDate(new java.sql.Date(date.getTime()));

        surroundingConditionRepo.save(condition);

    }

}
