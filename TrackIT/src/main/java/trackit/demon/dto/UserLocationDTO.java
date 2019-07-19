package trackit.demon.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLocationDTO {
    private String ip;
    private String city;
    private String region;
    private String country;

    public UserLocationDTO(String ip, String city, String region, String country) {
        this.ip = ip;
        this.city = city;
        this.region = region;
        this.country = country;
    }

    public static UserLocationDTO of(String ip, String city, String region, String country) {
        return new UserLocationDTO(ip, city, region, country);
    }
}
