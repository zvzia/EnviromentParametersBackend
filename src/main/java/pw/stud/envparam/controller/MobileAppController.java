package pw.stud.envparam.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pw.stud.envparam.dao.SensorConfigEn;
import pw.stud.envparam.dao.SensorEn;
import pw.stud.envparam.dao.MeasurementEn;
import pw.stud.envparam.model.*;
import pw.stud.envparam.service.MobileAppService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/mobile")
public class MobileAppController {

    MobileAppService mobileAppService;
    Logger logger = LoggerFactory.getLogger(MobileAppController.class);
    public MobileAppController(MobileAppService mobileAppService) {
        this.mobileAppService = mobileAppService;
    }

    @PostMapping(value = "/registration", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<StringResponse> registration(@RequestBody RegistrationRequest registrationRequest){
        logger.info("/registration\n" + registrationRequest.toString());
        Error err = mobileAppService.registration(registrationRequest);
        if (err != null){
            String msg = new JSONObject().put("err", err.getMessage()).toString();
            return new ResponseEntity<>(new StringResponse(msg), HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(new StringResponse("OK"));
    }

    @PostMapping(value = "/login", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest){
        logger.info("/login\n" + loginRequest.toString());
        UserResponse user;
        try {
            user = mobileAppService.login(loginRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(user);
    }


    @PostMapping(value = "/getRecordsForSensorInRange", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ArrayResponse> getRecordsForSensorInRange(@RequestBody DataRangeRequest dataRangeRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        logger.info("/getRecordsForSensorInRange\n" + dataRangeRequest.toString() + "\n email: " + email);
        List<MeasurementEn> elements = mobileAppService.getRecordsForSensorInRange(dataRangeRequest);
        ArrayResponse<MeasurementEn> result = new ArrayResponse<MeasurementEn>();
        result.setElements(elements);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/getSensorsList", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ArrayResponse> getSensorsList(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        logger.info("/getSensorsList\n" + "\n email: " + email);
        List<SensorEn> elements = mobileAppService.getSensorsList(email);
        ArrayResponse<SensorEn> result = new ArrayResponse<SensorEn>();
        result.setElements(elements);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/getSensorById", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Optional<SensorEn>> getSensorById(@RequestBody SensorIdRequest sensorIdReq){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        logger.info("/getSensorById\n" + sensorIdReq.toString() + "\n email: " + email);
        Optional<SensorEn> sensor = null;
        try {
            sensor = mobileAppService.getSensorById(sensorIdReq, email);
        } catch (Exception e) {
            new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(sensor);
    }

    @GetMapping(value = "/getLastUpdateDateString", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<DateStringResponse> getLastUpdateDateString(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        logger.info("/getLastUpdateDateString\n" + "\n email: " + email);
        DateStringResponse dateStringResponse = new DateStringResponse();
        dateStringResponse.setLastUpdateDate(mobileAppService.getLastUpdateDateString(email));
        return ResponseEntity.ok(dateStringResponse);
    }

    @PostMapping(value = "/getLastRecordsForSensors", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ArrayResponse> getLastRecordsForSensors(@RequestBody SensorsIdsRequest sensorsIds){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        logger.info("/getLastRecordsForSensors\n" + sensorsIds.toString() + "\n email: " + email);
        List<MeasurementEn> elements = mobileAppService.getLastRecordsForSensors(sensorsIds, email);
        ArrayResponse<MeasurementEn> result = new ArrayResponse<MeasurementEn>();
        result.setElements(elements);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/getLastBatteryLevel", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<BatteryLevelResponse> getLastBatteryLevels(@RequestParam int sensorId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        logger.info("/getLastBatteryLevel\n" + sensorId + "\n email: " + email);
        BatteryLevelResponse batteryLevel = new BatteryLevelResponse();
        batteryLevel.setSensorId(sensorId);
        batteryLevel.setBatteryLvl(mobileAppService.getLastBatteryLevel(sensorId, email));

        return ResponseEntity.ok(batteryLevel);
    }

    @PatchMapping(value = "/updateSensorName", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<StringResponse> updateSensorName(@RequestBody Sensor sensor){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        logger.info("/updateSensorName\n" + sensor.toString() + "\n email: " + email);
        //TODO zmienić w aplikacji
        mobileAppService.updateSensorName(sensor);

        return ResponseEntity.ok(new StringResponse("OK"));
    }

    @PostMapping(value = "/addSensor", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<StringResponse> addSensor(@RequestBody AddSensorRequest addSensorReq){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        logger.info("/addSensor\n" + addSensorReq.toString() + "\n email: " + email);
        mobileAppService.addSensor(addSensorReq, email);
        
        return ResponseEntity.ok(new StringResponse("OK"));
    }


    @PatchMapping(value = "/setSensorConfig", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<StringResponse> setSensorConfig(@RequestBody SensorConfigRequest sensorConfig){
        mobileAppService.setSensorConfig(sensorConfig);
        return ResponseEntity.ok(new StringResponse("OK"));
    }

    @GetMapping(value = "/getSensorConfig", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<SensorConfigResponse> getSensorConfig(@RequestParam int sensorId){
        Map<String, Object> map = mobileAppService.getSensorConfig(sensorId);

        if(map != null){
            SensorConfigResponse sensorConfigResponse = new SensorConfigResponse();
            sensorConfigResponse.setSensorName((String) map.get("name"));

            SensorConfigEn config = (SensorConfigEn) map.get("config");
            sensorConfigResponse.setMeasurementFreq(config.getMeasurmentFrequency());
            sensorConfigResponse.setTemperatureMin(config.getMinTemp());
            sensorConfigResponse.setTemperatureMax(config.getMaxTemp());
            sensorConfigResponse.setHumidityMin(config.getMinHumidity());
            sensorConfigResponse.setHumidityMax(config.getMaxHumidity());

            return ResponseEntity.ok(sensorConfigResponse);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/setUserInfo", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<UserResponse> setUserInfo(@RequestBody UserInfoRequest userInfoRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        UserResponse user = mobileAppService.setUserInfo(email, userInfoRequest);
        return ResponseEntity.ok(user);
    }

    @PatchMapping(value = "/setUserMsgToken", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<StringResponse> setUserMsgToken(@RequestBody MessageToken messageToken){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        mobileAppService.setUserMsgToken(email, messageToken);
        return ResponseEntity.ok(new StringResponse("OK"));
    }
}