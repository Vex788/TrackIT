package trackit.demon.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import trackit.demon.dto.UserCabinetDTO;
import trackit.demon.model.SiteData;

@Component
public class EmailServiceImpl implements EmailService {

    private static final String FROM = "vex788g@gmail.com";

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private ApplicationContext applicationContext;

    public void sendMessage(UserCabinetDTO userCabinetDTO, SiteData siteData, double currentPrice) {
        SimpleMailMessage message = applicationContext.getBean(SimpleMailMessage.class);

        String text = String.format(message.getText(), siteData.getStartedPrice(), currentPrice, siteData.getSiteUrl());

        message.setFrom(FROM);
        message.setTo(userCabinetDTO.getEmail());
        message.setText(text);

        emailSender.send(message);
    }
}