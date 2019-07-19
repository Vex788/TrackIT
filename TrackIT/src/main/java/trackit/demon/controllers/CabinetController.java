package trackit.demon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import trackit.demon.dto.UserCabinetDTO;
import trackit.demon.html_parser.SearchValueInHtml;
import trackit.demon.message.Message;
import trackit.demon.model.CUser;
import trackit.demon.model.SiteData;
import trackit.demon.services.UserServiceImpl;

import java.util.ArrayList;

@RestController
@RequestMapping("/cabinet")
public class CabinetController {

    @Autowired
    private UserServiceImpl userService;

    private CUser getDBUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        System.out.println(username);
        CUser dbCUser = userService.findByEmail(username);

        return dbCUser;
    }

    @GetMapping("/get")
    public ResponseEntity<UserCabinetDTO> onGet() {
        CUser dbCUser = getDBUser();

        if (dbCUser != null) {
            return new ResponseEntity<>(dbCUser.toUserCabinetDTO(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add_task")
    public ResponseEntity<Message> onAddTask(@RequestBody SiteData siteData) {
        CUser dbCUser = getDBUser();

        if (dbCUser != null) {
            if (!dbCUser.getSiteDataCollection().contains(siteData)) {
                SearchValueInHtml search = new SearchValueInHtml();
                boolean found = search.getData(siteData.getSiteUrl(), String.valueOf(siteData.getStartedPrice()));

                if (found) {
                    siteData.setXmlPathToPriceElement(search.getXPath());
                    dbCUser.addSearchStructureToCollection(siteData);
                    userService.updateUser(dbCUser);

                    return new ResponseEntity<>(
                            new Message(dbCUser.getId(),
                                    "/add_task",
                                    "Price found."),
                            HttpStatus.OK
                    );
                } else {
                    return new ResponseEntity<>(
                            new Message(dbCUser.getId(),
                                    "/add_task",
                                    "Price not found."),
                            HttpStatus.NOT_FOUND
                    );
                }
            } else {
                return new ResponseEntity<>(
                        new Message(dbCUser.getId(),
                                "/add_task",
                                "Already exists."),
                        HttpStatus.IM_USED
                );
            }
        }

        return new ResponseEntity<>(
                new Message("/add_task",
                        "Unauthorized."),
                HttpStatus.UNAUTHORIZED
        );
    }

    @PostMapping("/edit_task")
    public ResponseEntity<Message> onEditTask(@RequestParam("index") int index,
                                             @RequestBody SiteData siteData) {
        if (index > -1) {
            CUser dbCUser = getDBUser();

            if (dbCUser != null) {
                new ArrayList<>(dbCUser.getSiteDataCollection()).set(index, siteData); // update data in element
                userService.updateUser(dbCUser);

                return new ResponseEntity<>(
                        new Message(dbCUser.getId(),
                                "/edit_task",
                                "Edit task success."),
                        HttpStatus.OK
                );
            } else {
                return new ResponseEntity<>(
                        new Message("/edit_task",
                                "Unauthorized."),
                        HttpStatus.UNAUTHORIZED);
            }
        }

        return new ResponseEntity<>(
                new Message("/edit_task",
                        "Some error."),
                HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete_task")
    public ResponseEntity<Message> onDeleteTask(@RequestParam(name = "toDelete[]", required = false) long[] ids) {
        if (ids != null) {
            userService.deleteSiteDataCollection(getDBUser(), ids);
            return new ResponseEntity<>(
                    new Message("/delete_task",
                            "Delete task success."),
                    HttpStatus.OK);
        }

        return new ResponseEntity<>(
                new Message("/edit_task",
                        "Some error."),
                HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/spring_test")
    public ResponseEntity<Message> onSpringTest() {
        System.out.println(userService.findByEmail("testvex@mail.com").getPassword());
        if (getDBUser() != null)
        return new ResponseEntity<>(new Message("/spring_test", "test success"), HttpStatus.OK);
        return new ResponseEntity<>(
                new Message("/spring_test",
                        "Unauthorized."),
                HttpStatus.UNAUTHORIZED);
    }
}
