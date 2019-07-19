package trackit.demon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import trackit.demon.model.CUser;
import trackit.demon.model.UserRole;
import trackit.demon.repository.UserRepository;

import java.util.List;

@Service
@EnableTransactionManagement
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public void clear() {
        userRepository.deleteAll();
    }

    @Transactional
    public List<CUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public CUser findById(long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public CUser findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Transactional
    public CUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public boolean existsByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Transactional
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public boolean existsByEmailOrNickname(String email, String nickname) {
        return userRepository.existsByEmailOrNickname(email, nickname);
    }

    @Transactional
    public void deleteUsers(long[] ids) {
        if (ids == null) return;

        for (long id : ids) {
            userRepository.deleteById(id);
        }
    }

    @Transactional
    public void deleteSiteDataCollection(CUser CUser, long[] ids) {
        if (ids == null) return;

        for (long id : ids) {
            CUser.getSiteDataCollection().remove(id);
        }
    }

    @Transactional
    public boolean addUser(CUser CUser) {
        if (CUser == null) return false;
        if (userRepository.existsByEmailOrNickname(CUser.getEmail(), CUser.getNickname()))
            return false;

        userRepository.save(CUser);
        return true;
    }

    @Transactional
    public boolean addUser(String nickname, String passHash,
                           UserRole role, String email,
                           String phoneNumber) {
        if (email == null || passHash == null) return false;
        if (userRepository.existsByEmailOrNickname(email, nickname))
            return false;

        CUser CUser = new CUser(nickname, passHash, role, email, phoneNumber, false, null);
        userRepository.save(CUser);

        return true;
    }

    @Transactional
    public boolean updateUser(CUser CUser) {
        if (CUser == null) return false;
        if (!userRepository.existsByEmail(CUser.getEmail()))
            return false;

        userRepository.save(CUser);

        return true;
    }
}
