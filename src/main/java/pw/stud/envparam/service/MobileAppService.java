package pw.stud.envparam.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import pw.stud.envparam.dao.*;
import pw.stud.envparam.model.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MobileAppService {

    @Autowired
    MeasurementRepo measurementRepo;
    @Autowired
    SensorRepo sensorRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    SensorConfigRepo sensorConfigRepo;

    Logger logger = LoggerFactory.getLogger(MobileAppService.class);

    public UserResponse login(LoginRequest loginRequest) throws Exception {
        Optional<UserEn> userOpt = userRepo.findByEmail(loginRequest.getEmail());
        if (userOpt.isPresent() && new BCryptPasswordEncoder().matches(loginRequest.getPassword(), userOpt.get().getPassword())) {
            UserEn userEn = userOpt.get();
            UserResponse user = new UserResponse(userEn.getId(), userEn.getUsername(), userEn.getEmail(), loginRequest.getPassword());
            return user;
        } else {
            throw new Exception("user not found");
        }
    }

    public Error registration(RegistrationRequest registrationRequest) {
        if (userRepo.findByEmail(registrationRequest.getEmail()).isPresent()) {
            return new Error("user already exists");
        }

        UserEn user = new UserEn();
        user.setUsername(registrationRequest.getUsername());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(registrationRequest.getPassword()));
        userRepo.save(user);

        return null;
    }

    public ArrayList<MeasurementEn> getRecordsForSensorInRange(@RequestBody DataRangeRequest rangeModel) {
        Timestamp startSql = new java.sql.Timestamp(rangeModel.getStart().getTime());
        Timestamp endSql = new java.sql.Timestamp(rangeModel.getEnd().getTime());
        ArrayList<MeasurementEn> surroundingConditions = measurementRepo.findRecordsForSensorInRange(rangeModel.getSensorId(), startSql, endSql);
        return surroundingConditions;
    }

    public ArrayList<SensorEn> getSensorsList(String email) {
        int userId = 0;
        try {
            userId = getUserId(email);
        } catch (Exception e) {
            logger.info("user " + email + "not found");
            return null;
        }
        ArrayList<SensorEn> sensors = new ArrayList<SensorEn>(sensorRepo.findAllByUserId(userId));
        return sensors;
    }

    public Optional<SensorEn> getSensorById(@RequestBody SensorIdRequest sensorIdReq, String email) throws Exception {
        int userId = 0;
        try {
            userId = getUserId(email);
        } catch (Exception e) {
            logger.info("user " + email + "not found");
            return null;
        }

        Optional<SensorEn> sensor = sensorRepo.findById(sensorIdReq.getSensorId());
        if (!sensor.isPresent() || sensor.get().getUserId() != userId) {
            throw new Exception("unauthorized");
        }
        return sensor;
    }

    public String getLastUpdateDateString(String email) {
        int userId = 0;
        try {
            userId = getUserId(email);
        } catch (Exception e) {
            logger.info("user " + email + "not found");
            return null;
        }

        Timestamp date = measurementRepo.findLastUpdateDate(userId);
        if(date == null){
            return "";
        }
        return date.toString();
    }

    public ArrayList<MeasurementEn> getLastRecordsForSensors(@RequestBody SensorsIdsRequest sensorsIds, String email) {
        int userId = 0;
        try {
            userId = getUserId(email);
        } catch (Exception e) {
            logger.info("user " + email + "not found");
            return null;
        }

        ArrayList<MeasurementEn> surroundingConditions = new ArrayList<>();
        //TODO sprawdzanie user id
        for (int id : sensorsIds.getSensorIds()) {
            surroundingConditions.add(measurementRepo.getLastRecordForSensor(id));
        }
        return surroundingConditions;
    }

    public int getLastBatteryLevel(int sensorId, String email) {
        int userId = 0;
        try {
            userId = getUserId(email);
        } catch (Exception e) {
            logger.info("user " + email + "not found");
        }

        //TODO sprawdzanie user id
        int batteryLevel = measurementRepo.findLastBatteryLevelBySensorId(sensorId);
        return batteryLevel;
    }

    public void updateSensorName(@RequestBody Sensor sensor) {
        sensorRepo.updateSensorName(sensor.getId(), sensor.getName());
    }

    public void addSensor(@RequestBody AddSensorRequest addSensorReq, String email) {
        int userId = 0;
        try {
            userId = getUserId(email);
        } catch (Exception e) {
            logger.info("user " + email + "not found");
            return;
        }

        SensorEn sensorEn = new SensorEn();
        sensorEn.setName(addSensorReq.getName());
        sensorEn.setToken(addSensorReq.getToken());
        sensorEn.setUserId(userId);

        sensorRepo.save(sensorEn);
    }

    public void setSensorConfig(@RequestBody SensorConfigRequest sensorConfig) {
        sensorRepo.updateSensorName(sensorConfig.getSensorId(), sensorConfig.getSensorName());

        Optional<SensorConfigEn> sensorConfigEn = sensorConfigRepo.findSensorConfigEnBySensorId(sensorConfig.getSensorId());
        if(sensorConfigEn.isPresent()){
            sensorConfigRepo.updateSensorConfig(
                    sensorConfig.getSensorId(),
                    sensorConfig.getMeasurementFreq(),
                    (double)sensorConfig.getTemperatureMax(),
                    (double)sensorConfig.getTemperatureMin(),
                    sensorConfig.getHumidityMax(),
                    sensorConfig.getHumidityMin());
        }else {
            SensorConfigEn newSensorConfigEn = new SensorConfigEn(sensorConfig.getSensorId(),
                    sensorConfig.getMeasurementFreq(),
                    (double)sensorConfig.getTemperatureMax(),
                    (double)sensorConfig.getTemperatureMin(),
                    sensorConfig.getHumidityMax(),
                    sensorConfig.getHumidityMin());
            sensorConfigRepo.save(newSensorConfigEn);
        }


    }

    public Map<String, Object> getSensorConfig(@RequestParam int sensorId) {
        Optional<SensorConfigEn> sensorConfigEn = sensorConfigRepo.findSensorConfigEnBySensorId(sensorId);
        Optional<SensorEn> sensorEn = sensorRepo.findById(sensorId);

        if(sensorConfigEn.isPresent() && sensorEn.isPresent()){
            Map<String, Object> resp = new HashMap<>();
            resp.put("name", sensorEn.get().getName());
            resp.put("config", sensorConfigEn.get());
            return resp;
        }

        return null;
    }


    private int getUserId(String email) throws Exception {
        Optional<UserEn> user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            return user.get().getId();
        } else {
            throw new Exception("user not found");
        }
    }

}
