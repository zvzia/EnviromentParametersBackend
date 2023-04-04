package pw.stud.envparam.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import pw.stud.envparam.DbMock;
import pw.stud.envparam.model.*;

import java.util.ArrayList;

@Service
public class MobileAppService {

    public ArrayList<SurroundingConditions> getRecordsForSensorInRange(@RequestBody DataRangeRequest rangeModel){
        DbMock dbMock = new DbMock();
        ArrayList<SurroundingConditions> surroundingConditions = dbMock.getRecordsForSensorInRange(rangeModel.getSensorId(), rangeModel.getStart(), rangeModel.getEnd());
        return surroundingConditions;
    }

    public ArrayList<Sensor> getSensorsList(){
        DbMock dbMock = new DbMock();
        ArrayList<Sensor> sensors = dbMock.getSensorsList();
        return sensors;
    }
    public Sensor getSensorById(@RequestBody SensorIdRequest sensorIdReq){
        Sensor sensor = new DbMock().getSensorById(sensorIdReq.getSensorId());
        return sensor;
    }

    public String getLastUpdateDateString(){
        String lastUpdate = new DbMock().getLastUpdateDateString();
        return lastUpdate;
    }

    public ArrayList<SurroundingConditions> getLastRecordsForSensors(@RequestBody SensorsIdsRequest sensorsIds){
        DbMock dbMock = new DbMock();
        ArrayList<SurroundingConditions> surroundingConditions = dbMock.getLastRecordsForSensors(sensorsIds.getSensorIds());
        return surroundingConditions;
    }


}
