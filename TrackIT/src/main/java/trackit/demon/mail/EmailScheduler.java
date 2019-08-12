package trackit.demon.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import trackit.demon.html.parser.SearchValueInHtml;
import trackit.demon.model.CUser;
import trackit.demon.model.SiteData;
import trackit.demon.services.UserServiceImpl;

import java.util.List;

@Component
public class EmailScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserServiceImpl userService;

    private SearchValueInHtml search;

    private boolean conditionIsMet(double startedPrice, double finishPrice, boolean increase, double currentPrice) {
        if (finishPrice != 0) {
            if (startedPrice > finishPrice) {
                if (finishPrice >= currentPrice) {
                    return true;
                }
            }
            if (startedPrice < finishPrice) {
                if (finishPrice <= currentPrice) {
                    return true;
                }
            }
        } else {
            if (increase) {
                if (startedPrice < currentPrice) {
                    return true;
                }
            } else {
                if (startedPrice > currentPrice) {
                    return true;
                }
            }
        }
        return false;
    }

    @Scheduled(fixedRate = 60000) // every 30 minutes
    public void sendNotifications() {
        System.out.println("Starting scheduler");
        List<CUser> userList = userService.getAllUsers();

        if (userList != null && !userList.isEmpty()) {
            for (CUser user : userList) {
                if (user.getSiteDataCollection() != null && !user.getSiteDataCollection().isEmpty()) {
                    for (SiteData siteData : user.getSiteDataCollection()) {
                        search = new SearchValueInHtml();
                        String[] currencyCodes = new String[2];
                        currencyCodes = siteData.getCurrencyCodes().split("/"); // get currency usd/uah -> { "usd", "uah" }

                        boolean found = search.getData(siteData.getSiteUrl(), currencyCodes[0], currencyCodes[1]); // search price on a page

                        if (true) {
                            /*if (conditionIsMet(
                                    siteData.getStartedPrice(),
                                    siteData.getFinishPrice(),
                                    siteData.isIncrease(),
                                    Double.parseDouble(search.getPriceValue()
                                    ))) {*/
                                emailService.sendMessage(user.toUserCabinetDTO(), siteData, Double.parseDouble(search.getPriceValue()));

                                user.getSiteDataCollection().remove(siteData);
                                userService.updateUser(user);
                           // }
                        }
                    }
                }
            }
        }
    }
}
