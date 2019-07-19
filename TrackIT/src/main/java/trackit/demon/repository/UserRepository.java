package trackit.demon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trackit.demon.model.CUser;

@Repository
public interface UserRepository extends JpaRepository<CUser, Long> {

    CUser findById(long id);

    CUser findByNickname(String nickname);

    boolean existsByNickname(String nickname);

    CUser findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByEmailOrNickname(String email, String nickname);
}
