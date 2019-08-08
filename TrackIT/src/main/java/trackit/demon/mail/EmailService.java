package trackit.demon.mail;

import trackit.demon.dto.UserCabinetDTO;
import trackit.demon.model.SiteData;

public interface EmailService {
    void sendMessage(UserCabinetDTO userCabinetDTO, SiteData siteData, double currentPrice);

    void sendVerifyEmailMessage(UserCabinetDTO userCabinetDTO, String urlWithToken);
}
