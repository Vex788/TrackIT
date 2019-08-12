package trackit.demon.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import trackit.demon.mail.EmailService;
import trackit.demon.model.CUser;
import trackit.demon.model.UserRole;
import trackit.demon.providers.CustomAuthenticationProvider;
import trackit.demon.services.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class AuthHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    private String getRandomPassword() {
        return new Random()
                .ints(10, 33, 122)
                .mapToObj(i -> String.valueOf((char)i))
                .collect(Collectors.joining());
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken)authentication;
        OAuth2User user = token.getPrincipal();

        Map<String, Object> attributes = user.getAttributes();
        CUser newUser = null;

        if (!userService.existsByEmail((String) attributes.get("email"))) {
            newUser = new CUser();
            newUser.setEmail((String) attributes.get("email"));
            newUser.setNickname((String) attributes.get("name"));
            newUser.setPassword(getRandomPassword());
            newUser.setRole(UserRole.USER);
            newUser.setOnline(true);
            newUser.setAccountConfirmed(true);

            userService.addUser(newUser);
        } else {
            newUser = userService.findByEmail((String) attributes.get("email"));
            newUser.setOnline(true);

            userService.updateUser(newUser);
        }

        // create session for redirect to cabinet
        // Create users data cookies
        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(newUser.getEmail(), newUser.getPassword());

        // Authenticate the user
        Authentication auth = authenticationProvider.authenticate(authRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);

        // Create a new session and add the security context.
        HttpSession session = httpServletRequest.getSession(true);
        session.setMaxInactiveInterval(10 * 60);
        session.removeAttribute("SPRING_SECURITY_CONTEXT");
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        httpServletResponse.sendRedirect("/cabinet");
    }
}
