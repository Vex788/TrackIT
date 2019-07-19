package trackit.demon.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationDTO {
    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;

    public RegistrationDTO(String email, String password, String nickname, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }

    public static RegistrationDTO of(String email, String password, String nickname, String phoneNumber) {
        return new RegistrationDTO(email, password, nickname, phoneNumber);
    }
}
