package pw.stud.envparam.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.stud.envparam.model.ArrayResponse;
import pw.stud.envparam.model.DataRangeRequest;
import pw.stud.envparam.model.SurroundingConditions;
import pw.stud.envparam.service.MobileAppService;
import pw.stud.envparam.service.SensorService;

import java.util.List;

@RestController
@RequestMapping("/sensor")
public class SensorController {
    SensorService sensorService = new SensorService();

    @PostMapping(value = "/getRecordsForSensorInRange", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<String> getRecordsForSensorInRange(@RequestBody SurroundingConditions surroundingConditions){

        return ResponseEntity.ok("OK");
    }
}
