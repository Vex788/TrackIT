package trackit.demon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import trackit.demon.message.Message;
import trackit.demon.model.CUser;
import trackit.demon.services.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/login")
    public String onLogin() {
        return "login";
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

    @GetMapping("/logout")
    public ResponseEntity<Message> onLogout() {
        return new ResponseEntity<>(
                new Message("/logout", "Logout success."),
                HttpStatus.OK
        );
    }
}
