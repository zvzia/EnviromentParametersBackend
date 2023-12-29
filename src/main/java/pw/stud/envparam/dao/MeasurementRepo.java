package pw.stud.envparam.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;

@Repository
public interface MeasurementRepo extends JpaRepository<MeasurementEn,Integer> {

    @Query(value = "SELECT * FROM measurement WHERE sensor_id=:sensorId AND date>=:start AND date<=:end", nativeQuery = true)
    ArrayList<MeasurementEn> findRecordsForSensorInRange(int sensorId, Timestamp start, Timestamp end);

    @Query(value = "SELECT sc.date FROM measurement sc JOIN sensor s ON sc.sensor_id = s.id WHERE s.user_id =:userId AND date=(SELECT MAX(date) FROM measurement);", nativeQuery = true)
    Timestamp findLastUpdateDate(int userId);

    @Query(value = "SELECT TOP 1 * FROM measurement WHERE sensor_id=:sensorId ORDER BY id DESC", nativeQuery = true)
    MeasurementEn getLastRecordForSensor(int sensorId);

    @Query(value = "SELECT TOP 1 battery_level FROM measurement WHERE sensor_id=:sensorId ORDER BY id DESC", nativeQuery = true)
    int findLastBatteryLevelBySensorId(int sensorId);
}
