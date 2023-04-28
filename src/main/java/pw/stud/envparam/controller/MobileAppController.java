package pw.stud.envparam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pw.stud.envparam.dao.SensorEn;
import pw.stud.envparam.dao.SurroundingConditionEn;
import pw.stud.envparam.model.*;
import pw.stud.envparam.service.MobileAppService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mobile")
public class MobileAppController {

    MobileAppService mobileAppService;
    public MobileAppController(MobileAppService mobileAppService) {
        this.mobileAppService = mobileAppService;
    }

    @PostMapping(value = "/getRecordsForSensorInRange", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ArrayResponse> getRecordsForSensorInRange(@RequestBody DataRangeRequest dataRangeRequest){
        List<SurroundingConditionEn> elements = mobileAppService.getRecordsForSensorInRange(dataRangeRequest);
        ArrayResponse<SurroundingConditionEn> result = new ArrayResponse<SurroundingConditionEn>();
        result.setElements(elements);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/getSensorsList", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ArrayResponse> getSensorsList(){
        List<SensorEn> elements = mobileAppService.getSensorsList();
        ArrayResponse<SensorEn> result = new ArrayResponse<SensorEn>();
        result.setElements(elements);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/getSensorById", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Optional<SensorEn>> getSensorById(@RequestBody SensorIdRequest sensorIdReq){
        Optional<SensorEn> sensor = mobileAppService.getSensorById(sensorIdReq);
        return ResponseEntity.ok(sensor);
    }

    @GetMapping(value = "/getLastUpdateDateString", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<DateStringResponse> getLastUpdateDateString(){
        DateStringResponse dateStringResponse = new DateStringResponse();
        dateStringResponse.setLastUpdateDate(mobileAppService.getLastUpdateDateString());
        return ResponseEntity.ok(dateStringResponse);
    }

    @PostMapping(value = "/getLastRecordsForSensors", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ArrayResponse> getLastRecordsForSensors(@RequestBody SensorsIdsRequest sensorsIds){
        List<SurroundingConditionEn> elements = mobileAppService.getLastRecordsForSensors(sensorsIds);
        ArrayResponse<SurroundingConditionEn> result = new ArrayResponse<SurroundingConditionEn>();
        result.setElements(elements);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/getLastBatteryLevel", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<BatteryLevelResponse> getLastBatteryLevels(@RequestBody SensorIdRequest sensorIdReq){
        BatteryLevelResponse batteryLevel = new BatteryLevelResponse();
        batteryLevel.setSensorId(sensorIdReq.getSensorId());
        batteryLevel.setBatteryLvl(mobileAppService.getLastBatteryLevel(sensorIdReq));

        return ResponseEntity.ok(batteryLevel);
    }

    @PostMapping(value = "/updateSensorName", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<String> updateSensorName(@RequestBody Sensor sensor){
        mobileAppService.updateSensorName(sensor);

        return ResponseEntity.ok("OK");
    }


}