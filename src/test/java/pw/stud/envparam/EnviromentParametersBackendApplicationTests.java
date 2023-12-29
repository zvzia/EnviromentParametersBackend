package pw.stud.envparam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pw.stud.envparam.dao.SensorEn;
import pw.stud.envparam.dao.SensorRepo;

import java.util.ArrayList;

@SpringBootTest
class EnviromentParametersBackendApplicationTests {

    @Autowired
    SensorRepo sensorRepo;
    @Test
    void contextLoads() {
    }

    @Test
    void getTest() {
        ArrayList<SensorEn> sensors = new ArrayList<SensorEn>(sensorRepo.findAllByUserId(2));
        System.out.println(sensors.size());
    }

}
