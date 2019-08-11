package trackit.demon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trackit.demon.model.CUser;

import java.beans.Transient;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        CUser CUser = userService.findByEmail(username);
        System.out.println("impl-----" + username);
        System.out.println("impl-----" + CUser.getNickname());

        if (CUser == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(CUser.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(CUser.getEmail(), CUser.getPassword(), roles);
    }
}