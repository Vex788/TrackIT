package trackit.demon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import trackit.demon.model.UserLocation;
import trackit.demon.repository.UserLocationRepository;

import java.sql.Date;
import java.util.ArrayList;

@Service
@EnableTransactionManagement
public class UserLocationServiceImpl implements UserLocationService {

    @Autowired
    private UserLocationRepository userLocationRepository;

    @Transactional
    public ArrayList<UserLocation> findAllByIp(String ip) {
        return userLocationRepository.findAllByIp(ip);
    }

    @Transactional
    public ArrayList<UserLocation> findAllByCountry(String country) {
        return userLocationRepository.findAllByCountry(country);
    }

    @Transactional
    public ArrayList<UserLocation> findAllByRegion(String region) {
        return userLocationRepository.findAllByRegion(region);
    }

    @Transactional
    public UserLocation findByIp(String ip) {
        return userLocationRepository.findByIp(ip);
    }

    @Transactional
    public UserLocation findByCountry(String country) {
        return userLocationRepository.findByCountry(country);
    }

    @Transactional
    public UserLocation findByRegion(String region) {
        return userLocationRepository.findByRegion(region);
    }

    @Transactional
    public boolean existsByIp(String ip) {
        return userLocationRepository.existsByIp(ip);
    }

    @Transactional
    public void deleteUserLocations(long[] ids) {
        for (long id : ids) {
            userLocationRepository.deleteById(id);
        }
    }

    @Transactional
    public void addUserLocation(UserLocation userLocation) {
        UserLocation userLocation2 = userLocationRepository.findByIp(userLocation.getIp());

        if (userLocation2 != null) {
            userLocation2.setDate(userLocation.getDate());
            userLocationRepository.save(userLocation2);
        }

        userLocationRepository.save(userLocation);
    }

    @Transactional
    public void addUserLocation(String ip, String city, String country, String region, Date date) {
        UserLocation userLocation = userLocationRepository.findByIp(ip);

        if (userLocation == null) {
            userLocation = new UserLocation(ip, city, country, region, date);
        } else {
            userLocation.setDate(date);
        }

        userLocationRepository.save(userLocation);
    }

    @Transactional
    public void updateUserLocation(UserLocation userLocation) {
        userLocationRepository.save(userLocation);
    }
}
