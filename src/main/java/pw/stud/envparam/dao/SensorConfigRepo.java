package pw.stud.envparam.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SensorConfigRepo extends JpaRepository<SensorConfigEn,Integer>{
    Optional<SensorConfigEn> findSensorConfigEnBySensorId(int sensorId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE sensor_config SET measurment_frequency=:measurmentFrequency, max_temp=:maxTemp, min_temp=:minTemp, max_humidity=:maxHumidity, min_humidity=:minHumidity WHERE sensor_id=:sensorId ", nativeQuery = true)
    void updateSensorConfig(int sensorId, int measurmentFrequency, Double maxTemp, Double minTemp, Integer maxHumidity, Integer minHumidity);
}
