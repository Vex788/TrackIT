package trackit.demon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import trackit.demon.message.Message;
import trackit.demon.model.CUser;
import trackit.demon.model.UserLocation;
import trackit.demon.retrievers.LocationRetriever;
import trackit.demon.services.UserLocationServiceImpl;
import trackit.demon.services.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@Controller
public class IndexController implements ErrorController {

    @Autowired
    private UserLocationServiceImpl userLocationService;

    @Autowired
    private LocationRetriever locationRetriever;

    @Autowired
    private UserServiceImpl userService;

    private CUser getDBUser(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");

        CUser dbCUser = userService.findByEmail(securityContext.getAuthentication().getName());

        return dbCUser;
    }


    private boolean isAnonymousUser(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");

        if (securityContext == null) {
            return true;
        }
        if (securityContext.getAuthentication().getName().toLowerCase().equals("anonymoususer")) {
            return true;
        }

        return false;
    }
    // ToDo: refactor index page
    @GetMapping("/")
    public String onIndex(HttpServletRequest request) {
        /*String ip = request.getRemoteAddr();

        UserLocation userLocation = locationRetriever.getLocation(ip);

        if (userLocation.getRegion() != null) {
            userLocation.setDate(new Date(System.currentTimeMillis()));

            System.out.println(request.getHeader("X-Forwarded-For") + " - " + ip);
            System.out.println(
                    userLocation.getCity() + "\n" +
                            userLocation.getCountry() + "\n" +
                            userLocation.getRegion()
            );

            userLocationService.addUserLocation(userLocation);
        }*/

        return "login";
    }

    @GetMapping("/cabinet")
    public String onCabinet(HttpServletRequest request, HttpServletResponse response) {
        if (isAnonymousUser(request)) {
            try {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized request.");
                return "error";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "cabinet";
    }

    @GetMapping("/email_check")
    public String onEmailCheck() {
        return "email_check";
    }

    @GetMapping("/login")
    public String onLogin() {
        return "login";
    }

    @GetMapping("/payment")
    public String onPayment(HttpServletRequest request, HttpServletResponse response) {
        if (isAnonymousUser(request)) {
            try {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized request.");
                return "redirect:/error";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "payment";
    }

    @GetMapping("/registration")
    public String onRegistration() {
        return "registration";
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        String errorMsg = "";
        int errorCode = getErrorCode(request);

        switch (errorCode) {
            case 400: {
                errorMsg = "Http Error Code: 400. Bad Request";
                break;
            }
            case 401: {
                errorMsg = "Http Error Code: 401. Unauthorized";
                break;
            }
            case 404: {
                errorMsg = "Http Error Code: 404. Resource Not Found";
                break;
            }
            case 405: {
                errorMsg = "Http Error Code: 405. Method Not Allowed";
                break;
            }
            case 415: {
                errorMsg = "Http Error Code: 415. Unsupported Media Type";
                break;
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Internal Server Error";
                break;
            }
            case 503: {
                errorMsg = "Http Error Code: 503. Service Unavailable";
                break;
            }
        }

        request.setAttribute("error_code", errorCode);
        request.setAttribute("error_msg", errorMsg);

        return "error";
    }

    private int getErrorCode(HttpServletRequest request) {
        return (Integer) request.getAttribute("javax.servlet.error.status_code");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/logout")
    public ResponseEntity<Message> onLogout(HttpServletRequest request) {
        CUser user = getDBUser(request);

        if (user != null) {
            user.setOnline(false);
            userService.updateUser(user);

            HttpSession session = request.getSession(true);
            session.removeAttribute("SPRING_SECURITY_CONTEXT");
        }
        return new ResponseEntity<>(
                new Message("/logout", "Logout success."),
                HttpStatus.OK
        );
    }
}
