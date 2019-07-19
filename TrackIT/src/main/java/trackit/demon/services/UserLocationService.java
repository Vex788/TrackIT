package trackit.demon.services;

import trackit.demon.model.UserLocation;

import java.sql.Date;
import java.util.ArrayList;

public interface UserLocationService {

    ArrayList<UserLocation> findAllByIp(String ip);

    ArrayList<UserLocation> findAllByCountry(String country);

    ArrayList<UserLocation> findAllByRegion(String region);

    UserLocation findByIp(String ip);

    UserLocation findByCountry(String country);

    UserLocation findByRegion(String region);

    boolean existsByIp(String ip);

    void deleteUserLocations(long[] ids);

    void addUserLocation(UserLocation userLocation);

    void addUserLocation(String ip, String city, String country, String region, Date date);

    void updateUserLocation(UserLocation userLocation);
}
