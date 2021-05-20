package com.api.rc_paradise_api.security.Config;

import com.api.rc_paradise_api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    //Overiding the authenitcation provider
    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(bCryptPasswordEncoder);

        return provider;

    }
    //Overiding the configure method of WebSecurityConfigurerAdapter
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/product")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/product/search")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/product/{productId}")
                .permitAll()

                .antMatchers(HttpMethod.GET, "/api/v1/cart/{sellerId}")
                .permitAll()
                .antMatchers("/api/v1/register/user")
                .permitAll()

                 //CORS Policy issue

                //CART API
                .antMatchers(HttpMethod.POST, "/api/v1/sendSMS")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/validateOTP")

                //PRODUCT API
                .permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/product/add")
                .permitAll()
                .antMatchers(HttpMethod.PUT, "/api/v1/product/update")
                .permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/v1/product/delete/{productId}")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/product/seller?sellerId=${sellerId}&query=${query}")
                .permitAll()


                .anyRequest()
                .authenticated()
                 .and()
                .httpBasic();

    }
}
