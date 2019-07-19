package trackit.demon.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import trackit.demon.dto.RegistrationDTO;
import trackit.demon.dto.UserCabinetDTO;
import trackit.demon.dto.UserLoginDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class CUser {
    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String password;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String phoneNumber;

    private String ip;

    @ElementCollection
    private Collection<SiteData> siteDataCollection;

    private boolean isOnline;

    public CUser(String nickname, String password, UserRole role, String email, String phoneNumber, boolean isOnline, String ip) {
        this.nickname = nickname;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isOnline = isOnline;
        this.ip = ip;
    }

    public void addSearchStructureToCollection(SiteData siteData) {
        siteDataCollection.add(siteData);
    }

    public static CUser of(String email, String password, boolean isOnline) {
        return new CUser(null, password, null, email, null, isOnline, null);
    }

    public UserLoginDTO toUserLoginDTO() {
        return UserLoginDTO.of(email, password, isOnline);
    }

    public static CUser fromDTO(UserLoginDTO dto) {
        return of(dto.getEmail(), dto.getPassword(), dto.isOnline());
    }

    public static CUser of(String email, String password, String nickname, String phoneNumber) {
        return new CUser(nickname, password, UserRole.USER, email, phoneNumber, false, "undefined");
    }

    public RegistrationDTO toRegistrationDTO() {
        return RegistrationDTO.of(email, password, nickname, phoneNumber);
    }

    public static CUser fromDTO(RegistrationDTO dto) {
        return of(dto.getEmail(), dto.getPassword(), dto.getNickname(), dto.getPhoneNumber());
    }

    public static CUser of(Long id, String email, String nickname, UserRole role, String phoneNumber,
                           Collection<SiteData> siteDataCollection, boolean isOnline) {
        return new CUser(nickname, null, role, email, phoneNumber, isOnline, null);
    }

    public UserCabinetDTO toUserCabinetDTO() {
        return UserCabinetDTO.of(id, email, nickname, role, phoneNumber, siteDataCollection, isOnline);
    }

    public static CUser fromDTO(UserCabinetDTO dto) {
        return of(dto.getId(), dto.getEmail(), dto.getNickname(), dto.getRole(), dto.getPhoneNumber(),
                dto.getSiteDataCollection(), dto.isOnline());
    }
}
