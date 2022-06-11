package ru.otus.homework13.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import ru.otus.homework13.service.impl.ReaderService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final ReaderService readerService;

    @Autowired
    public SecurityConfig(ReaderService readerService) {
        this.readerService = readerService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(readerService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance()); // only for homework
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("**/books").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("**/books/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("**/comments").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("**/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("j_login")
                .passwordParameter("j_password")
                .defaultSuccessUrl("/books", true)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}
