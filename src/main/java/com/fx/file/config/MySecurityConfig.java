package com.fx.file.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${name}")
    private String name;
    @Value("${pwd}")
    private String password;
    @Value("${staticAutorize}")
    private boolean staticAutorize;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        // 定制请求授权的规则
        http.authorizeRequests()
                .antMatchers("/**").hasRole("admin");

        http.csrf().disable();
        // 开启自动配置的登录功能,
        http.formLogin();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser(name).password(new BCryptPasswordEncoder().encode(password)).roles("admin");
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        //super.configure(web);
        if(!staticAutorize)
        web.ignoring().antMatchers("/static/**");
    }
}
