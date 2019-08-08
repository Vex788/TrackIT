package trackit.demon.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationDTO {
    private String email;
    private String password;
    private String password2;
    private String nickname;
    private String phoneNumber;

    public RegistrationDTO(String email, String password, String password2, String nickname, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.password2 = password2;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }

    public static RegistrationDTO of(String email, String password, String password2, String nickname, String phoneNumber) {
        return new RegistrationDTO(email, password, password2, nickname, phoneNumber);
    }
}
