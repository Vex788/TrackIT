package trackit.demon.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import trackit.demon.dto.UserLocationDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
public class UserLocation {
    @Id
    @GeneratedValue
    private long id;

    private String ip;

    private String city;

    private String country;

    private String region;

    private Date date;

    public UserLocation(String ip, String city, String country, String region, Date date) {
        this.ip = ip;
        this.city = city;
        this.country = country;
        this.region = region;
        this.date = date;
    }

    public static UserLocation of(String ip, String city, String country, String region, Date date) {
        return new UserLocation(ip, city, country, region, date);
    }

    public UserLocationDTO toDTO() {
        return UserLocationDTO.of(ip, city, country, region);
    }

    public static UserLocation fromDTO(UserLocationDTO dto) {
        return of(dto.getIp(), dto.getCity(), dto.getCountry(), dto.getRegion(), new Date(System.currentTimeMillis()));
    }
}
