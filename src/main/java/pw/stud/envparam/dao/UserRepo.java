package pw.stud.envparam.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEn,Integer> {
    Optional<UserEn> findByUsername(String username);
    Optional<UserEn> findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE [user] SET username=:name, email=:email, password=:password   WHERE id=:id ", nativeQuery = true)
    void updateUserInfo(int id, String name, String email, String password);
}
