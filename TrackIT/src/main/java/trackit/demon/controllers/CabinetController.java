package trackit.demon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import trackit.demon.dto.RegistrationDTO;
import trackit.demon.dto.UserCabinetDTO;
import trackit.demon.html.parser.SearchValueInHtml;
import trackit.demon.mail.EmailService;
import trackit.demon.message.Message;
import trackit.demon.model.CUser;
import trackit.demon.model.SiteData;
import trackit.demon.services.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/cabinet")
public class CabinetController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    private CUser getDBUser(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");

        CUser dbCUser = userService.findByEmail(securityContext.getAuthentication().getName());

        return dbCUser;
    }

    @PostMapping("/edit")
    public ResponseEntity<Message> onEdit(@RequestBody RegistrationDTO registrationDTO,
                                          HttpServletRequest request) {
        ResponseEntity<Message> responseEntity = null;
        CUser user = getDBUser(request);

        if (user != null) {
            if (user.isAccountConfirmed()) {
                if (!registrationDTO.getEmail().isEmpty() && !registrationDTO.getEmail().equals(user.getEmail())) {
                    user.setEmail(registrationDTO.getEmail());
                    user.setAccountConfirmed(false);

                    new Thread(() -> emailService.sendVerifyEmailMessage(user.toUserCabinetDTO(),
                            "http://localhost:8080/email/verify?id=" + user.getId() +
                                    "&token=" + passwordEncoder.encode(user.getEmail()))).start();
                }
                if (!registrationDTO.getNickname().isEmpty()) {
                    user.setNickname(registrationDTO.getNickname());
                }
                // For delete and added phone number
                user.setPhoneNumber(registrationDTO.getPhoneNumber());

                userService.updateUser(user);

                responseEntity = new ResponseEntity<>(
                        new Message("/cabinet/edit",
                                "Data edited."),
                        HttpStatus.OK
                );
            } else {
                responseEntity = new ResponseEntity<>(
                        new Message(user.getId(),
                                "/cabinet/add_task",
                                "Email confirmation required."),
                        HttpStatus.NOT_EXTENDED
                );
            }
        }
        if (responseEntity == null) {
            responseEntity = new ResponseEntity<>(
                    new Message("/cabinet/edit",
                            "Unauthorized."),
                    HttpStatus.UNAUTHORIZED
            );
        }
        return responseEntity;
    }

    @GetMapping("/get")
    public ResponseEntity<UserCabinetDTO> onGet(HttpServletRequest request) {
        CUser dbCUser = getDBUser(request);

        if (dbCUser != null) {
            return new ResponseEntity<>(dbCUser.toUserCabinetDTO(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    // ToDo: add html integrity
    @PostMapping("/add_task")
    public ResponseEntity<Message> onAddTask(@RequestBody SiteData siteData, HttpServletRequest request) {
        ResponseEntity<Message> responseEntity = null;
        CUser dbCUser = getDBUser(request);

        if (dbCUser != null) {
            if (dbCUser.isAccountConfirmed()) {
                if (dbCUser.getRole().toString().equals("USER") && dbCUser.getSiteDataCollection().size() < 5
                        || dbCUser.getRole().toString().equals("VIP") && dbCUser.getSiteDataCollection().size() < 25
                        || dbCUser.getRole().toString().equals("ADMIN") && dbCUser.getSiteDataCollection().size() < 100) {
                    if ((siteData.getSiteUrl() != null && !siteData.getSiteUrl().isEmpty())
                            || siteData.getCurrencyCodes() != null && !siteData.getCurrencyCodes().isEmpty()) {
                        SearchValueInHtml search = new SearchValueInHtml();
                        String[] currencyCodes = new String[2];

                        if (siteData.getSiteUrl() == null || siteData.getSiteUrl().isEmpty()
                                && siteData.getCurrencyCodes().split("/").length == 2) {
                            currencyCodes = siteData.getCurrencyCodes().split("/"); // get 2 currency codes
                        }

                        boolean found = search.getData(siteData.getSiteUrl().trim(), currencyCodes[0], currencyCodes[1]);

                        if (found) {
                            siteData.setSiteTitle(search.getSiteTitle());
                            siteData.setStartedPrice(Double.parseDouble(search.getPriceValue()));

                            if (!dbCUser.addSearchStructureToCollection(siteData)) {
                                return new ResponseEntity<>(
                                        new Message(dbCUser.getId(),
                                                "/cabinet/add_task",
                                                "Already exists."),
                                        HttpStatus.NOT_EXTENDED
                                );
                            }

                            userService.updateUser(dbCUser);

                            responseEntity = new ResponseEntity<>(
                                    new Message(dbCUser.getId(),
                                            "/cabinet/add_task",
                                            "Title: " + siteData.getSiteTitle()
                                                    + "<br>Current price: " + siteData.getStartedPrice() + "<br>"),
                                    HttpStatus.OK
                            );
                        } else {
                            responseEntity = new ResponseEntity<>(
                                    new Message(dbCUser.getId(),
                                            "/cabinet/add_task",
                                            "Price not found."),
                                    HttpStatus.NOT_FOUND
                            );
                        }
                    } else {
                        responseEntity = new ResponseEntity<>(
                                new Message(dbCUser.getId(),
                                        "/cabinet/add_task",
                                        "Empty all fields."),
                                HttpStatus.BAD_REQUEST
                        );
                    }
                } else {
                    responseEntity = new ResponseEntity<>(
                            new Message(dbCUser.getId(),
                                    "/cabinet/add_task",
                                    "Registered users can track up to 5 trackers.<br>If you bought a subscription, then the maximum number of 25 trackers."),
                            HttpStatus.LENGTH_REQUIRED
                    );
                }
            } else {
                responseEntity = new ResponseEntity<>(
                        new Message(dbCUser.getId(),
                                "/cabinet/add_task",
                                "Email confirmation required."),
                        HttpStatus.NOT_EXTENDED
                );
            }
        }
        if (responseEntity == null) {
            responseEntity = new ResponseEntity<>(
                    new Message("/cabinet/add_task",
                            "Unauthorized."),
                    HttpStatus.UNAUTHORIZED
            );
        }
        return responseEntity;
    }
    // Editing task coming soon
    /*@PostMapping("/edit_task")
    public ResponseEntity<Message> onEditTask(@RequestParam("index") int index,
                                              HttpServletRequest request) {
        ResponseEntity<Message> responseEntity = null;
        if (index > -1) {
            CUser dbCUser = getDBUser(request);

            if (dbCUser != null) {
                new ArrayList<>(dbCUser.getSiteDataCollection())
                        .set(index, (SiteData) dbCUser.getSiteDataCollection().toArray()[index]); // update data in element
                userService.updateUser(dbCUser);

                responseEntity = new ResponseEntity<>(
                        new Message(dbCUser.getId(),
                                "/cabinet/edit_task",
                                "Edit task success."),
                        HttpStatus.OK
                );
            } else {
                responseEntity = new ResponseEntity<>(
                        new Message("/cabinet/edit_task",
                                "Unauthorized."),
                        HttpStatus.UNAUTHORIZED);
            }
        }
        if (responseEntity == null) {
            responseEntity = new ResponseEntity<>(
                    new Message("/cabinet/edit_task",
                            "Some error."),
                    HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }*/

    @PostMapping("/delete_task")
    public ResponseEntity<Message> onDeleteTask(@RequestParam(name = "index") int index,
                                                HttpServletRequest request) {
        ResponseEntity<Message> responseEntity = null;

        if (index > -1) {
            CUser user = getDBUser(request);

            if (index < user.getSiteDataCollection().size()) {
                userService.deleteSiteDataElement(user, index);
                responseEntity = new ResponseEntity<>(
                        new Message("/cabinet/delete_task",
                                "Delete task success."),
                        HttpStatus.OK);
            }
        }
        if (responseEntity == null) {
            responseEntity = new ResponseEntity<>(
                    new Message("/cabinet/edit_task",
                            "Some error."),
                    HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
