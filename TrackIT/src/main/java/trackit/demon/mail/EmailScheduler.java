package trackit.demon.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import trackit.demon.html_parser.SearchValueInHtml;
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
    private boolean found = false;

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

    //@Scheduled(fixedDelay = 60000)
    public void sendNotifications() {
        List<CUser> CUserList = userService.getAllUsers();

        if (CUserList != null && !CUserList.isEmpty()) {
            for (CUser CUser : CUserList) {
                if (CUser.getSiteDataCollection() != null && !CUser.getSiteDataCollection().isEmpty()) {
                    for (SiteData siteData : CUser.getSiteDataCollection()) {
                        search = new SearchValueInHtml();
                        found = search.getData(siteData.getSiteUrl(), String.valueOf(siteData.getStartedPrice())); // search price on a page

                        if (found) {
                            if (conditionIsMet(
                                    siteData.getStartedPrice(),
                                    siteData.getFinishPrice(),
                                    siteData.isIncrease(),
                                    Double.parseDouble(search.getPriceValue()
                                    ))) {
                                emailService.sendMessage(CUser.toUserCabinetDTO(), siteData, Double.parseDouble(search.getPriceValue()));
                            }
                        }
                    }
                }
            }
        }
    }
}
