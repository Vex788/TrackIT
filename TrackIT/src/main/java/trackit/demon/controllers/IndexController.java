package trackit.demon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import trackit.demon.model.UserLocation;
import trackit.demon.retrievers.LocationRetriever;
import trackit.demon.services.UserLocationServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

@RestController
public class IndexController {

    @Autowired
    private UserLocationServiceImpl userLocationService;

    @Autowired
    private LocationRetriever locationRetriever;

    @GetMapping("/")
    public String onIndex(HttpServletRequest request) {
        String ip = request.getRemoteAddr();

        UserLocation userLocation = locationRetriever.getLocation(ip);
        userLocation.setDate(new Date(System.currentTimeMillis()));

        System.out.println(request.getHeader("X-Forwarded-For") + " - " + ip);
        System.out.println(
                        userLocation.getCity() + "\n" +
                        userLocation.getCountry() + "\n" +
                        userLocation.getRegion()
        );

        userLocationService.addUserLocation(userLocation);

        return "index";
    }
}
