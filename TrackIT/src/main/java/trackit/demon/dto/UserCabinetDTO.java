package trackit.demon.dto;

import lombok.Data;
import trackit.demon.model.SiteData;
import trackit.demon.model.UserRole;

import java.util.Collection;

@Data
public class UserCabinetDTO {
    private Long id;

    private String email;

    private String nickname;

    private UserRole role;

    private String phoneNumber;

    private Collection<SiteData> siteDataCollection;

    private boolean isOnline;

    public UserCabinetDTO(Long id, String email, String nickname, UserRole role, String phoneNumber,
                           Collection<SiteData> siteDataCollection, boolean isOnline) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.siteDataCollection = siteDataCollection;
        this.isOnline = isOnline;
    }

    public static UserCabinetDTO of(Long id, String email, String nickname, UserRole role, String phoneNumber,
                                    Collection<SiteData> siteDataCollection, boolean isOnline) {
        return new UserCabinetDTO(id, email, nickname, role, phoneNumber, siteDataCollection, isOnline);
    }
}
