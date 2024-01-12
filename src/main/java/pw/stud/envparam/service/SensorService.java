package pw.stud.envparam.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pw.stud.envparam.dao.*;
import pw.stud.envparam.model.DataFromSensorRequest;
import pw.stud.envparam.model.Note;
import pw.stud.envparam.model.SensorTokenRequest;

import java.util.Date;
import java.util.Optional;


@Service
public class SensorService {
    @Autowired
    MeasurementRepo measurementRepo;

    @Autowired
    SensorRepo sensorRepo;

    @Autowired
    SensorConfigRepo sensorConfigRepo;

    @Autowired
    FirebaseMessagingService firebaseMessagingService;

    @Autowired
    UserRepo userRepo;


    public void sendData(@RequestBody DataFromSensorRequest dataFromSensorRequest){
        Optional<SensorEn> sensor = sensorRepo.findByToken(dataFromSensorRequest.getToken());

        if (sensor.isPresent() && sensor.get().getToken().equals(dataFromSensorRequest.getToken())){
            MeasurementEn condition = new MeasurementEn();
            condition.setSensorId(sensor.get().getId());
            condition.setTemperature((double) dataFromSensorRequest.getTemperature());
            condition.setHumidity(dataFromSensorRequest.getHumidity());
            Date date = new Date();
            condition.setDate(new java.sql.Timestamp(date.getTime()));
            condition.setBatteryLevel(dataFromSensorRequest.getBatteryLevel());
            measurementRepo.save(condition);
        }else{
            return;
        }

        Optional<SensorConfigEn> sensorConfigEnOpt = sensorConfigRepo.findSensorConfigEnBySensorId(sensor.get().getId());
        if(sensorConfigEnOpt.isPresent()){
            Optional<UserEn> userEnOpt = userRepo.findById(sensor.get().getUserId());
            String token = "";
            if(userEnOpt.isPresent()){
                token = userEnOpt.get().getMsgToken();
                if(token == null || token.equals("")){
                    return;
                }
            }

            SensorConfigEn sensorConfigEn = sensorConfigEnOpt.get();
            boolean isTempOk = true;
            boolean isHumidOk = true;

            if(sensorConfigEn.getMinTemp() != null && dataFromSensorRequest.getTemperature() < sensorConfigEn.getMinTemp()){
                isTempOk = false;
            }
            if(sensorConfigEn.getMaxTemp() != null && dataFromSensorRequest.getTemperature() > sensorConfigEn.getMaxTemp()){
                isTempOk = false;
            }
            if(sensorConfigEn.getMinHumidity() != null && dataFromSensorRequest.getHumidity() < sensorConfigEn.getMinHumidity()){
                isHumidOk = false;
            }
            if(sensorConfigEn.getMaxHumidity() != null && dataFromSensorRequest.getHumidity() > sensorConfigEn.getMaxHumidity()){
                isHumidOk = false;
            }

            Note note = new Note();

            if(!isTempOk && !isHumidOk){
                note.setContent("Temperature: " + dataFromSensorRequest.getTemperature() + "°C\nHumidity: " + dataFromSensorRequest.getHumidity() + "%");
            }else if(!isTempOk){
                note.setContent("Temperature: " + dataFromSensorRequest.getTemperature() + "°C");
            }else if(!isHumidOk){
                note.setContent("Humidity: " + dataFromSensorRequest.getHumidity() + "%");
            }

            if(!isTempOk || !isHumidOk){
                note.setSubject("Parameters exceeded the set value");
                try {
                    firebaseMessagingService.sendNotification(note,token);
                } catch (FirebaseMessagingException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    public int getFrequency(SensorTokenRequest sensorTokenRequest) {
        Optional<SensorEn> sensorOpt = sensorRepo.findByToken(sensorTokenRequest.getToken());

        if(sensorOpt.isPresent()){
            Optional<SensorConfigEn> sensorConfigEn = sensorConfigRepo.findSensorConfigEnBySensorId(sensorOpt.get().getId());
            if(sensorConfigEn.isPresent() && sensorConfigEn.get().getMeasurmentFrequency() != null){
                return sensorConfigEn.get().getMeasurmentFrequency();
            }
        }
        return 30;
    }
}
