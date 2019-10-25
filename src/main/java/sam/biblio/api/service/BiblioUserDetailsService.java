package sam.biblio.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sam.biblio.api.model.security.Privilege;
import sam.biblio.api.model.security.Role;
import sam.biblio.api.model.security.User;
import sam.biblio.api.repository.security.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
//@Qualifier("userDetailsService")
@Transactional
public class BiblioUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpServletRequest request;

    public BiblioUserDetailsService() {
        super();
    }
    @Override
    public UserDetails loadUserByUsername(final String email) {
//        try {
            final Optional<User> user = userRepository.findByEmailIgnoreCase(email);
            if (!user.isPresent()) {
                throw new UsernameNotFoundException("No user found with username: " + email);
            }
            return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), user.get().isEnabled(), true, true, true, getAuthorities(user.get().getRoles()));
//        }
//        catch (final Exception e) {
//            throw new UsernameNotFoundException(e);
//        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    /**
     * Here, Privilege is synonym of User.Role
     * @param roles
     * @return
     */
    private List<String> getPrivileges(final Collection<Role> roles) {
        final List<String> privileges = new ArrayList<>();
        final List<Privilege> collection = new ArrayList<>();
        for (final Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (final Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    /**
     * Here, Authority is synonym of User.Role.Privilege
     * @param privileges
     * @return
     */
    private List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

}
