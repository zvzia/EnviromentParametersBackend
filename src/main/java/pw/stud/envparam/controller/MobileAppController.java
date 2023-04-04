package pw.stud.envparam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pw.stud.envparam.model.*;
import pw.stud.envparam.service.MobileAppService;

import java.util.List;

@RestController
@RequestMapping("/mobile")
public class MobileAppController {

    MobileAppService mobileAppService = new MobileAppService();

    @PostMapping(value = "/getRecordsForSensorInRange", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ArrayResponse> getRecordsForSensorInRange(@RequestBody DataRangeRequest dataRangeRequest){
        List<SurroundingConditions> elements = mobileAppService.getRecordsForSensorInRange(dataRangeRequest);
        ArrayResponse<SurroundingConditions> result = new ArrayResponse<SurroundingConditions>();
        result.setElements(elements);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/getSensorsList", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ArrayResponse> getSensorsList(){
        List<Sensor> elements = mobileAppService.getSensorsList();
        ArrayResponse<Sensor> result = new ArrayResponse<Sensor>();
        result.setElements(elements);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/getSensorById", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Sensor> getSensorById(@RequestBody SensorIdRequest sensorIdReq){
        Sensor sensor = mobileAppService.getSensorById(sensorIdReq);
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
        List<SurroundingConditions> elements = mobileAppService.getLastRecordsForSensors(sensorsIds);
        ArrayResponse<SurroundingConditions> result = new ArrayResponse<SurroundingConditions>();
        result.setElements(elements);
        return ResponseEntity.ok(result);
    }



}