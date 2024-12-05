package com.linzd.eurekaserver.configs;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Cloud Finchley及更高版本，必须添加如下代码，部分关闭掉Spring Security
 * 的CSRF保护功能，否则应用无法正常注册！
 *ref: http://cloud.spring.io/spring-cloud-netflix/single/spring-cloud-netflix.html#_securing_the_eureka_server
 * @author Lorenzo Lin
 * @params
 * @created 2020/1/12 12:19
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/eureka/**");
        http.headers().frameOptions().disable();
        super.configure(http);
    }
}