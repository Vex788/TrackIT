package trackit.demon.services;

import trackit.demon.model.CUser;
import trackit.demon.model.SiteData;
import trackit.demon.model.UserRole;

import java.util.Collection;
import java.util.List;

public interface UserService {
    long count();

    void clear();

    List<CUser> getAllUsers();

    CUser findById(long id);

    CUser findByNickname(String nickname);

    CUser findByEmail(String email);

    Collection<SiteData> getSiteDataCollection(long userID);

    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);

    boolean existsByEmailOrNickname(String email, String nickname);

    void deleteUsers(long[] ids);

    void deleteSiteDataCollection(CUser CUser, long[] ids);

    boolean addUser(CUser CUser);

    boolean addUser(String nickname, String passHash,
                    UserRole role, String email,
                    String phone);

    boolean updateUser(CUser CUser);
}
