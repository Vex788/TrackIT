package trackit.demon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import trackit.demon.model.CUser;
import trackit.demon.providers.CustomAuthenticationProvider;
import trackit.demon.services.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/email")
public class EmailVerifyController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    private boolean tokenVerifier(String email, String token) {
        return passwordEncoder.matches(email, token);
    }

    @GetMapping("/verify")
    public String onGet(@RequestParam("id") Long id,
                              @RequestParam("token") String token,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        CUser user = userService.findById(id);

        if (user.isAccountConfirmed()){
            request.setAttribute("success", 3);
            request.setAttribute("msg", "[Account already confirmed]");
        } else {
            if (tokenVerifier(user.getEmail(), token)) {
                user.setAccountConfirmed(true);
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

                request.setAttribute("success", 2);
                request.setAttribute("msg", "[Account verified]");
            } else {
                request.setAttribute("success", 1);
                request.setAttribute("msg", "[Account not verified]");
            }
        }
        return "/email_check";
    }
}
