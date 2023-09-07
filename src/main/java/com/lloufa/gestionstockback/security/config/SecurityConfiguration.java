package com.lloufa.gestionstockback.security.config;

import com.lloufa.gestionstockback.security.service.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final ApplicationUserDetailsService applicationUserDetailsService;

    private final ApplicationDetailsService applicationDetailsService;

    @Autowired
    public SecurityConfiguration(ApplicationUserDetailsService applicationUserDetailsService, ApplicationDetailsService applicationDetailsService) {
        this.applicationUserDetailsService = applicationUserDetailsService;
        this.applicationDetailsService = applicationDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.applicationUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**/authenticate",
                        "/**/v2/api-docs",
                        "/**/swagger-resources",
                        "/**/swagger-resources/**",
                        "/**/configuration/ui",
                        "/**/configuration/security",
                        "/**/swagger-ui.html",
                        "/**/swagger-ui/**",
                        "/**/webjars/**",
                        "/**/v3/api-docs/**")
                .permitAll()
                .antMatchers("/**/entreprises")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(applicationDetailsService, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    protected AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManagerBean();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/**/authenticate",
//                        "/**/v2/api-docs",
//                        "/**/swagger-resources",
//                        "/**/swagger-resources/**",
//                        "/**/configuration/ui",
//                        "/**/configuration/security",
//                        "/**/swagger-ui.html",
//                        "/**/swagger-ui/**",
//                        "/**/webjars/**",
//                        "/**/v3/api-docs/**")
//                .permitAll()
//                .antMatchers(HttpMethod.POST, "/**/entreprises")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        return http.addFilterBefore(applicationDetailsService, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }

}
