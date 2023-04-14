package pw.stud.envparam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pw.stud.envparam.dao.SensorEn;
import pw.stud.envparam.dao.SensorRepo;
import pw.stud.envparam.dao.SurroundingConditionEn;
import pw.stud.envparam.dao.SurroundingConditionRepo;
import pw.stud.envparam.model.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class MobileAppService {

    @Autowired
    SurroundingConditionRepo surroundingConditionRepo;
    @Autowired
    SensorRepo sensorRepo;

    public ArrayList<SurroundingConditionEn> getRecordsForSensorInRange(@RequestBody DataRangeRequest rangeModel){
        Timestamp startSql = new java.sql.Timestamp(rangeModel.getStart().getTime());
        Timestamp endSql = new java.sql.Timestamp(rangeModel.getEnd().getTime());
        ArrayList<SurroundingConditionEn> surroundingConditions = surroundingConditionRepo.findRecordsForSensorInRange(rangeModel.getSensorId(), startSql, endSql);
        return surroundingConditions;
    }

    public ArrayList<SensorEn> getSensorsList(){
        ArrayList<SensorEn> sensors = new ArrayList<SensorEn>(sensorRepo.findAll());
        return sensors;
    }
    public Optional<SensorEn> getSensorById(@RequestBody SensorIdRequest sensorIdReq){
        Optional<SensorEn> sensor = sensorRepo.findById(sensorIdReq.getSensorId());
        return sensor;
    }

    public String getLastUpdateDateString(){
        Timestamp date = surroundingConditionRepo.findLastUpdateDate();
        return date.toString();
    }

    public ArrayList<SurroundingConditionEn> getLastRecordsForSensors(@RequestBody SensorsIdsRequest sensorsIds){
        ArrayList<SurroundingConditionEn> surroundingConditions = new ArrayList<>();
        for (int id:sensorsIds.getSensorIds()) {
            surroundingConditions.add(surroundingConditionRepo.getLastRecordForSensor(id));
        }
        return surroundingConditions;
    }


}
