package pw.stud.envparam.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEn,Integer> {
    Optional<UserEn> findByUsername(String username);
    Optional<UserEn> findByEmail(String email);
}
