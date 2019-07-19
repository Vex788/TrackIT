package trackit.demon.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Bean
    @Scope("prototype")
    public SimpleMailMessage messageTemplate() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("TrackIT");
        message.setText("It seems it's time to buy it.\n" +
                "<center><img src=\"https://i.imgur.com/eFfmdb3.jpg\" alt=\"Fry says 'Shut up and take my money'\"></center>\n" +
                "Starting price [ %s ], current price [ %s ]\n" +
                "<a href=\"%s\">Go to page.</a>");

        return message;
    }
}
