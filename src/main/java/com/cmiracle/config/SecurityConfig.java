package com.cmiracle.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.cmiracle.service.AdminUserDetailService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminUserDetailService adminUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//        		.antMatchers("/admin/**").hasAuthority("ADMIN")
//                .antMatchers("/", "/user/**").permitAll()
//                .anyRequest().fullyAuthenticated()
//                .and()
//                .formLogin()
//                .loginPage("/admin_login")
//                .failureUrl("/admin_login?error")
//                .usernameParameter("username")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/admin_logout")
//                .deleteCookies("remember-me")
//                .logoutSuccessUrl("/admin/index")
//                .permitAll()
//                .and()
//                .rememberMe();
        
        
        http.authorizeRequests()
	        .antMatchers("/admin/**").hasRole("ADMIN")
	        .antMatchers("/**").permitAll().and()
	        .formLogin().and()
	        .logout().deleteCookies("remove").invalidateHttpSession(false)
            .logoutUrl("/logout").logoutSuccessUrl("/");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminUserDetailService);
    }

}