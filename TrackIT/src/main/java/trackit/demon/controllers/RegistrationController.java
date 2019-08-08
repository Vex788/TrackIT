package trackit.demon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import trackit.demon.dto.RegistrationDTO;
import trackit.demon.mail.EmailService;
import trackit.demon.message.Message;
import trackit.demon.model.CUser;
import trackit.demon.providers.CustomAuthenticationProvider;
import trackit.demon.services.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    @PostMapping("/new_user")
    public ResponseEntity<Message> onRegistration(@RequestBody RegistrationDTO registrationDTO,
                                                  HttpServletRequest request) {
        ResponseEntity<Message> responseEntity = null;
        boolean error = false;

        if (registrationDTO.getEmail() == null || registrationDTO.getEmail().isEmpty() ||
                registrationDTO.getNickname() == null || registrationDTO.getNickname().isEmpty()) {
            responseEntity = new ResponseEntity<>(
                    new Message("/registration/new_user", "Required fields:<br>" +
                            "\uD83D\uDCDD Nickname<br>" +
                            "\uD83D\uDCDD Email<br>" +
                            "\uD83D\uDCDD Password"),
                    HttpStatus.BAD_REQUEST
            );
            error = true;
        }
        if (!error && userService.existsByNickname(registrationDTO.getNickname())) {
            responseEntity = new ResponseEntity<>(
                    new Message("/registration/new_user", "Nickname already exists."),
                    HttpStatus.BAD_REQUEST
            );
            error = true;
        }
        if (!error && userService.existsByEmail(registrationDTO.getEmail())) {
            responseEntity = new ResponseEntity<>(
                    new Message("/registration/new_user", "Email already exists."),
                    HttpStatus.BAD_REQUEST
            );
            error = true;
        }
        if (!error && !registrationDTO.getPassword().equals(registrationDTO.getPassword2())) {
            responseEntity = new ResponseEntity<>(
                    new Message("/registration/new_user", "Passwords do not match."),
                    HttpStatus.BAD_REQUEST
            );
            error = true;
        }
        if (!error && registrationDTO.getPassword().length() < 8) {
            responseEntity = new ResponseEntity<>(
                    new Message("/registration/new_user", "Password length must be more than 8."),
                    HttpStatus.BAD_REQUEST
            );
            error = true;
        }
        if (responseEntity == null && !error) {
            // encode password
            registrationDTO.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
            // add user in db
            CUser newUser = CUser.fromDTO(registrationDTO);
            userService.addUser(newUser);
            // create session for redirect to cabinet
            // Create users data cookies
            UsernamePasswordAuthenticationToken authRequest =
                    new UsernamePasswordAuthenticationToken(newUser.getEmail(), newUser.getPassword());

            // Authenticate the user
            Authentication authentication = authenticationProvider.authenticate(authRequest);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            // Create a new session and add the security context.
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(10 * 60);
            session.removeAttribute("SPRING_SECURITY_CONTEXT");
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

            // Email confirmed
            new Thread(() -> emailService.sendVerifyEmailMessage(newUser.toUserCabinetDTO(),
                    "http://localhost:8080/email/verify?id=" + newUser.getId() +
                            "&token=" + passwordEncoder.encode(newUser.getEmail()))).start();

            responseEntity = new ResponseEntity<>(
                    new Message("/new_user", "Add user successfully.\n" +
                            "⚠ An email has been sent to your email, to confirm registration."),
                    HttpStatus.OK
            );
        }
        if (responseEntity == null) {
            responseEntity = new ResponseEntity<>(
                    new Message("/new_user", "Add user failed."),
                    HttpStatus.BAD_REQUEST
            );
        }
        return responseEntity;
    }

    //ToDo: add verify email
}
