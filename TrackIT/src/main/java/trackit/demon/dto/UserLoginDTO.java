package trackit.demon.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginDTO {
    private String email;
    private String password;
    private boolean isOnline;

    public UserLoginDTO(String email, String password, boolean isOnline) {
        this.email = email;
        this.password = password;
        this.isOnline = isOnline;
    }

    public static UserLoginDTO of(String email, String password, boolean isOnline) {
        return new UserLoginDTO(email, password, isOnline);
    }
}
