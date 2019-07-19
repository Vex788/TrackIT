package trackit.demon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trackit.demon.model.UserLocation;

import java.util.ArrayList;

@Repository
public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {

    ArrayList<UserLocation> findAllByIp(String ip);

    ArrayList<UserLocation> findAllByCountry(String country);

    ArrayList<UserLocation> findAllByRegion(String region);

    UserLocation findByIp(String ip);

    UserLocation findByCountry(String country);

    UserLocation findByRegion(String region);

    boolean existsByIp(String ip);
}
