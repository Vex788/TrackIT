package trackit.demon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import trackit.demon.model.CUser;
import trackit.demon.model.SiteData;
import trackit.demon.model.UserRole;
import trackit.demon.services.UserServiceImpl;

@SpringBootApplication
public class DemonApplication {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl userService;

    public static void main(String[] args) {
        SpringApplication.run(DemonApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return strings -> {
            CUser user = new CUser(
                    "admin_vex",
                    passwordEncoder.encode("vex"),
                    UserRole.ADMIN,
                    "vex788@ukr.net",
                    "0123456789",
                    false,
                    "123.45.67.89"
            );
            user.setAccountConfirmed(true);

            /*SiteData siteData = new SiteData();

            siteData.setSiteTitle("some description");
            siteData.setSiteUrl("some_url");
            siteData.setStartedPrice(22.21d);
            siteData.setFinishPrice(19.54);

            SiteData siteData2 = new SiteData();

            siteData2.setSiteTitle("some description");
            siteData2.setSiteUrl("some_url");
            siteData2.setStartedPrice(22.21d);
            siteData2.setIncrease(false);

            SiteData siteData3 = new SiteData();

            siteData3.setSiteTitle("some description");
            siteData3.setSiteUrl("some_url");
            siteData3.setStartedPrice(82.21d);
            siteData3.setFinishPrice(16.41);

            user.addSearchStructureToCollection(siteData);
            user.addSearchStructureToCollection(siteData2);
            user.addSearchStructureToCollection(siteData3);*/

            if (userService.addUser(user)) System.out.println("Admin added.");
        };
    }
}
