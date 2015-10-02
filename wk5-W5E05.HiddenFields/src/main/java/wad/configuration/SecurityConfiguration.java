package wad.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // ei päästetä käyttäjää mihinkään sovelluksen resurssiin ilman
        // kirjautumista
        http.authorizeRequests().anyRequest().authenticated();
        
        http.formLogin().permitAll().and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");

        // tarjotaan mahdollisuus kirjautumiseen ja annetaan kaikille
        // pääsy kirjautumissivulle
        http.formLogin().permitAll();
    }

    @Configuration
    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            // käyttäjällä jack, jonka salasana on bauer, on rooli USER
            auth.inMemoryAuthentication()
                    .withUser("user").password("password").roles("USER");
            auth.inMemoryAuthentication()
                    .withUser("postman").password("pat").roles("POSTER");            
        }
    }
}