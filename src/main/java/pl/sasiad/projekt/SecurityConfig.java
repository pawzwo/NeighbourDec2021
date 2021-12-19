package pl.sasiad.projekt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.sasiad.projekt.service.SpringDataUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**", "/available/**", "/parking/**","/occupied/**").hasAnyRole("USER", "ADMIN")
                .and().formLogin().loginPage("/signIn").defaultSuccessUrl("/user/main").failureUrl("/signIn?error=true")
                .and().rememberMe().key("uniqueAndSecret").rememberMeParameter("rememberMe").tokenValiditySeconds(120)
                .and().logout().logoutUrl("/perform_logout").logoutSuccessUrl("/").permitAll();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }

}
