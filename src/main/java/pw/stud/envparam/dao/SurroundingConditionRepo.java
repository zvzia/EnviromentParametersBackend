package pw.stud.envparam.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurroundingConditionRepo extends JpaRepository<SurroundingConditionEn,Integer> {
}
