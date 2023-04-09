package pw.stud.envparam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.stud.envparam.dao.SensorEn;
import pw.stud.envparam.dao.SensorRepo;
import pw.stud.envparam.dao.SurroundingConditionRepo;
import pw.stud.envparam.model.DataFromSensorRequest;
import pw.stud.envparam.model.SurroundingCondition;
import pw.stud.envparam.service.SensorService;

@RestController
@RequestMapping("/sensor")
public class SensorController {
    SensorService sensorService = new SensorService();

    @PostMapping(value = "/sendData", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<String> sendData(@RequestBody DataFromSensorRequest dataFromSensorRequest){
        System.out.println(dataFromSensorRequest.toString());

        sensorService.sendData(dataFromSensorRequest);

        return ResponseEntity.ok("OK");
    }
}
