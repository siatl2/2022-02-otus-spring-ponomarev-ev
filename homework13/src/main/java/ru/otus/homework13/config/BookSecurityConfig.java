package ru.otus.homework13.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import ru.otus.homework13.service.impl.ReaderService;

import javax.annotation.Resource;

@EnableWebSecurity
public class BookSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private ReaderService readerService;

    //@Autowired
//    public BookSecurityConfig(ReaderService readerService) {
//        this.readerService = readerService;
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(readerService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance()); // only for homework
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable().csrf().disable()
                .authorizeRequests()
                .mvcMatchers("**/books/save").hasRole("ADMIN")
                .mvcMatchers("**/books/delete*").hasRole("ADMIN")
                .mvcMatchers("**/books").hasAnyRole("READER", "ADMIN")
                .mvcMatchers("**/books/**").hasAnyRole("READER", "ADMIN")
                .mvcMatchers("**/comments").hasAnyRole("READER", "ADMIN")
                .mvcMatchers("**/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
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
