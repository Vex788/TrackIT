package trackit.demon;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import trackit.demon.dto.RegistrationDTO;
import trackit.demon.message.Message;
import trackit.demon.model.CUser;
import trackit.demon.model.SiteData;
import trackit.demon.model.UserRole;
import trackit.demon.services.UserServiceImpl;

import java.util.Map;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemonApplicationTests {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl userService;

    private int createSomeUsers(int count) {
        if (userService.count() > 0) userService.clear();

        CUser user;
        int usersAdded = 0;

        for (int i = 1; i <= count; i++) {
            user = new CUser(
                    "nick" + i,
                    passwordEncoder.encode("name" + i),
                    UserRole.USER,
                    "name" + i + "@mail.com",
                    "0123456789",
                    false,
                    "123.45.67.89"
            );
            if (userService.addUser(user)) {
                usersAdded++;

            }
        }

        return usersAdded;
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testAddUsers() {
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:8080/registration/new_user";

        int count = createSomeUsers(10);
        Assert.assertEquals(count, 10);

        for (int i = 11; i <= 20; i++) {
            RegistrationDTO registrationDTO = new RegistrationDTO(
                    "name" + i + "@mail.com",
                    "name" + i,
                    "nick" + i,
                    "0123456789");

            System.out.println(registrationDTO.getNickname() + "\n"
                    + registrationDTO.getEmail() + "\n"
                    + registrationDTO.getPassword() + "\n"
                    + registrationDTO.getPhoneNumber());

            ResponseEntity<Message> result = restTemplate.postForEntity(baseUrl, registrationDTO, Message.class);

            System.out.println(result.getBody().getMessage());
            // Verify request succeed
            Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        }

        Assert.assertEquals(20, userService.count());
    }

    @Test
    public void testAddTask() {
        System.out.println("Count users: " + userService.count());

        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:8080/cabinet/add_task";
        createSomeUsers(10);

        for (int i = 0 ; i < 1; i++) {
            SiteData siteData = new SiteData(
                    "https://rozetka.com.ua/xiaomi_mi_band_3_bk/p52131492/",
                    null,
                    699,
                    599
            );

            ResponseEntity<Message> result = restTemplate.postForEntity(baseUrl, siteData, Message.class);

            System.out.println(result.getBody().getMessage());
            Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        }
        Assert.assertEquals(10, 10);
    }

    @Test
    public void testLogin() {
        CUser user = new CUser(
                "vex788",
                passwordEncoder.encode("testvex"),
                UserRole.ADMIN,
                "testvex@mail.com",
                "0123456789",
                false,
                "123.45.67.89"
        );

        if (userService.addUser(user)) System.out.println("Admin added.");

        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<Message> result = restTemplate
                .withBasicAuth("testvex@mail.com","testvex")
                .getForEntity("http://localhost:8080/cabinet/spring_test", Message.class);
        System.out.println(result.getBody().getMessage());
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testDeleteTask() {

    }

    @Test
    public void testEditTask() {

    }
}
