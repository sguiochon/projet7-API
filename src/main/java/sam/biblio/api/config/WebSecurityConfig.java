package sam.biblio.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sam.biblio.api.service.BiblioUserDetailsService;

/**
 * Configuration de la sécurité (accès aux ressources web, mécanique d'authentification)
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BiblioUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**", "/",  "/documents", "/documents/**", "/copies", "/copies/*", "/api/**", "/error").permitAll()
                .antMatchers("/resa", "/pret").hasAuthority("USER")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied-error-page")
                //.and()
                //    .formLogin()
                //    .loginPage("/login")
                //    .successForwardUrl("/")
                //    .failureForwardUrl("/login-error")
                //    .permitAll()
                .and()
                    .logout()
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                .and()
                    .rememberMe()
                .and().csrf().disable();
    }

    /**
     * Déclaration du fournisseur d'authentification
     * @return l'instance du fournisseur d'authentification
     */
    @Bean
    public DaoAuthenticationProvider authProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    /**
     * Déclaration du bean utilisé par le fournisseur d'authentification pour encoder les mdp.
     * @return le bean chargé d'encodé les mdp
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }


}
