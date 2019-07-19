package trackit.demon.retrievers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import trackit.demon.dto.UserLocationDTO;
import trackit.demon.model.UserLocation;

@Component
public class LocationRetriever {

    private static final String URL = "http://ipinfo.io/";

    public UserLocation getLocation(String ip) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserLocationDTO> response = restTemplate.getForEntity(URL + ip, UserLocationDTO.class);

        return UserLocation.fromDTO(response.getBody());
    }
}
