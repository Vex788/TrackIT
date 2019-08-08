package trackit.demon.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginViaDTO {

    private String google_name = null;
    private String google_url = null;
    private String facebook_name = null;
    private String facebook_url = null;

    public LoginViaDTO(String google_name, String google_url, String facebook_name, String facebook_url) {
        this.google_name = google_name;
        this.google_url = google_url;
        this.facebook_name = facebook_name;
        this.facebook_url = facebook_url;
    }
}
