package pw.stud.envparam.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SensorRepo extends JpaRepository<SensorEn,Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE sensor SET name=:sensorName WHERE id=:sensorId ", nativeQuery = true)
    void updateSensorName(int sensorId, String sensorName);
}
