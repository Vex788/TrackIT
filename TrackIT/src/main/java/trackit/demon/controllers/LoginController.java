package trackit.demon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.bind.annotation.*;
import trackit.demon.dto.LoginViaDTO;
import trackit.demon.dto.UserLoginDTO;
import trackit.demon.message.Message;
import trackit.demon.model.CUser;
import trackit.demon.providers.CustomAuthenticationProvider;
import trackit.demon.services.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    private static String authorizationRequestBaseUri = "oauth2/authorization";

    private boolean passwordMatching(String passwordHash, String password) {
        return passwordEncoder.matches(password, passwordHash);
    }

    @PostMapping("/login")
    public ResponseEntity<Message> onLogin(@RequestBody UserLoginDTO userLoginDTO,
                                HttpServletRequest request) {
        ResponseEntity<Message> responseEntity = null;
        CUser user = userService.findByEmail(userLoginDTO.getEmail());

        if (user != null) {
            if (passwordMatching(user.getPassword(), userLoginDTO.getPassword())) {
                user.setIp(request.getRemoteAddr());
                user.setOnline(true);
                userService.updateUser(user);

                // Create users data cookies
                UsernamePasswordAuthenticationToken authRequest =
                        new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

                // Authenticate the user
                Authentication authentication = authenticationProvider.authenticate(authRequest);
                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authentication);

                // Create a new session and add the security context.
                HttpSession session = request.getSession(true);
                session.setMaxInactiveInterval(10 * 60);
                session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

                responseEntity = new ResponseEntity<>(
                        new Message("/login", "Success."),
                        HttpStatus.OK
                );
            } else {
                responseEntity = new ResponseEntity<>(
                        new Message("/login", "Passwords do not match."),
                        HttpStatus.NOT_ACCEPTABLE
                );
            }
        }
        if (userLoginDTO.getEmail().isEmpty() || userLoginDTO.getPassword().isEmpty()) {
            responseEntity = new ResponseEntity<>(
                    new Message("/login", "Empty text field."),
                    HttpStatus.BAD_REQUEST
            );
        }
        if (responseEntity == null) {
            responseEntity = new ResponseEntity<>(
                    new Message("/login", "Email not found."),
                    HttpStatus.NOT_FOUND
            );
        }
        return responseEntity;
    }

    @GetMapping("/oauth_login")
    public ResponseEntity<LoginViaDTO> getLoginPage(HttpServletResponse response) throws IOException {
        ClientRegistration clientRegistrationGoogle = clientRegistrationRepository
                .findByRegistrationId("google");
        ClientRegistration clientRegistrationFacebook = clientRegistrationRepository
                .findByRegistrationId("facebook");

        return new ResponseEntity<>(
                new LoginViaDTO(
                        clientRegistrationGoogle.getClientName(),
                        authorizationRequestBaseUri + "/" +
                                clientRegistrationGoogle.getRegistrationId(),
                        clientRegistrationFacebook.getClientName(),
                        authorizationRequestBaseUri + "/" +
                                clientRegistrationFacebook.getRegistrationId()),
                HttpStatus.OK
        );
    }

    @GetMapping("/login_status")
    public ResponseEntity<Message> onLoginStatus(HttpServletRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CUser dbCUser = userService.findByEmail(user.getUsername());

        if (dbCUser != null) {
            dbCUser.setIp(request.getRemoteAddr());
            dbCUser.setOnline(user.isEnabled());
            userService.updateUser(dbCUser);

            return new ResponseEntity<>(
                    new Message(dbCUser.getId(), "/login_status", "Login status is success."),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                new Message("/login_status", "Login status is failed."),
                HttpStatus.NOT_FOUND
        );
    }
    //ToDo: add login?error controller, logout?logout
}
