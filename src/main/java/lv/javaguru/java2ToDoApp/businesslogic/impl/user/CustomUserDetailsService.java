package lv.javaguru.java2ToDoApp.businesslogic.impl.user;

import lv.javaguru.java2ToDoApp.database.api.UserDAO;
import lv.javaguru.java2ToDoApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userDAO.getByLogin(login);
        //System.out.println("Getting access details from employee dao !!");
        if (!user.isPresent()) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        } else {
            //System.out.println(user.toString());
            return new org.springframework.security.core.userdetails.User(user.get().getLogin(), user.get().getPassword(), true, true, true, true, getGrantedAuthorities(user.get()));
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        //for(UserProfile userProfile : user.getUserProfiles()){
        //     System.out.println("UserProfile : "+userProfile);
        //     authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
        // }

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        //System.out.println("authorities :" + authorities);
        return authorities;
    }
}