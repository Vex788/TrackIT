package trackit.demon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import trackit.demon.dto.RegistrationDTO;
import trackit.demon.message.Message;
import trackit.demon.model.CUser;
import trackit.demon.services.UserServiceImpl;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/new_user")
    public ResponseEntity<Message> onRegistration(@RequestBody RegistrationDTO registrationDTO) {
        registrationDTO.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        if (userService.existsByEmail(registrationDTO.getEmail())) {
            return new ResponseEntity<>(
                    new Message("/new_user", "Email already exists."),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (userService.existsByNickname(registrationDTO.getNickname())) {
            return new ResponseEntity<>(
                    new Message("/new_user", "Nickname already exists."),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (userService.addUser(CUser.fromDTO(registrationDTO))) {
            return new ResponseEntity<>(
                    new Message("/new_user", "Add user successfully."),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                new Message("/new_user", "Add user failed."),
                HttpStatus.BAD_REQUEST
        );
    }

    //ToDo: add verify email
}
