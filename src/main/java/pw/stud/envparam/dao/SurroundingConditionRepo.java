package pw.stud.envparam.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;

@Repository
public interface SurroundingConditionRepo extends JpaRepository<SurroundingConditionEn,Integer> {

    @Query(value = "SELECT * FROM surrounding_condition WHERE sensor_id=:sensorId AND date>=:start AND date<=:end", nativeQuery = true)
    ArrayList<SurroundingConditionEn> findRecordsForSensorInRange(int sensorId, Timestamp start, Timestamp end);

    @Query(value = "SELECT date FROM surrounding_condition WHERE date=(SELECT MAX(date) FROM surrounding_condition)", nativeQuery = true)
    Timestamp findLastUpdateDate();

    @Query(value = "SELECT TOP 1 * FROM surrounding_condition WHERE sensor_id=:sensorId ORDER BY id DESC", nativeQuery = true)
    SurroundingConditionEn getLastRecordForSensor(int sensorId);

    @Query(value = "SELECT TOP 1 battery_level FROM surrounding_condition WHERE sensor_id=:sensorId ORDER BY id DESC", nativeQuery = true)
    int findLastBatteryLevelBySensorId(int sensorId);
}
