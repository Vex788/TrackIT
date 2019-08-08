package trackit.demon.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import trackit.demon.dto.UserCabinetDTO;
import trackit.demon.model.SiteData;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements EmailService {

    private static final String FROM = "trackit003@gmail.com";
    private String messageTemplate =
            "<center>\n" +
            "    <h1 style=\"display: inline-flex;\">Track<div style=\"color:green;\">IT</div></h1>\n" +
            "    <br>\n" +
            "    <h3 style=\"\n" +
            "    font-weight: bold;\n" +
            "\">It seems it's time to buy it.\n" +
            "        <br><img src=\"https://i.imgur.com/eFfmdb3.jpg\" alt=\"Fry says shut up and take my money\" width=\"562\" height=\"351\" style=\"margin-right: 0px;\"><br>\n" +
            "        Starting price [ %s ], current price [ %s ].<br><a href=\"%s\">Page with item</a>.\n" +
            "</h3></center>    \n" +
            "\n";
    private String confirmEmailTemplate =
            "<center><h2 style=\"display: inline-flex;\">Track<div style=\"color:green;\">IT</div></h2><br><h3>" +
            "To confirm the email click on the <a href=\"%s\">link</a></h3></center>";

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void sendMessage(UserCabinetDTO userCabinetDTO, SiteData siteData, double currentPrice) {
        try {
            JavaMailSenderImpl sender = applicationContext.getBean(JavaMailSenderImpl.class);

            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setSubject("Important message");
            helper.setFrom(FROM);
            helper.setTo(userCabinetDTO.getEmail());

            helper.setText(String.format(messageTemplate, siteData.getSiteTitle(), currentPrice, siteData.getSiteUrl()), true);

            sender.send(message);
        } catch (MailSendException mse) {
            mse.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }

    @Override
    public void sendVerifyEmailMessage(UserCabinetDTO userCabinetDTO, String urlWithToken) {
        try {
            JavaMailSenderImpl sender = applicationContext.getBean(JavaMailSenderImpl.class);

            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setSubject("Email confirmation");
            helper.setFrom(FROM);
            helper.setTo(userCabinetDTO.getEmail());

            helper.setText(String.format(confirmEmailTemplate, urlWithToken), true);

            sender.send(message);
        } catch (MailSendException mse) {
            mse.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}