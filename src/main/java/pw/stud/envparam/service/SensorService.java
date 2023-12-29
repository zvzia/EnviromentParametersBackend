package pw.stud.envparam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pw.stud.envparam.dao.*;
import pw.stud.envparam.model.DataFromSensorRequest;

import java.util.Date;
import java.util.Optional;


@Service
public class SensorService {
    @Autowired
    MeasurementRepo measurementRepo;

    @Autowired
    SensorRepo sensorRepo;


    public void sendData(@RequestBody DataFromSensorRequest dataFromSensorRequest){
        Optional<SensorEn> sensor = sensorRepo.findById(dataFromSensorRequest.getSensorId());

        if (sensor.isPresent() && sensor.get().getToken().equals(dataFromSensorRequest.getToken())){
            MeasurementEn condition = new MeasurementEn();
            condition.setSensorId(dataFromSensorRequest.getSensorId());
            condition.setTemperature((double) dataFromSensorRequest.getTemperature());
            condition.setHumidity(dataFromSensorRequest.getHumidity());
            Date date = new Date();
            condition.setDate(new java.sql.Timestamp(date.getTime()));
            condition.setBatteryLevel(dataFromSensorRequest.getBatteryLevel());
            measurementRepo.save(condition);
        }
    }
}
