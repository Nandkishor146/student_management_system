package com.studentmanagementsystem.student_management_system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
      /*security.authorizeRequests().antMatchers("/student/**","/enroll/**","course/**").hasRole("ADMIN")
                .antMatchers("/student/getAllStudents","/enroll/enrollStudent").hasRole("STUDENT")
                .antMatchers("/**").permitAll()
                .and()
                .build();*/

         return security
                .csrf(csrf->csrf.disable())
                .authorizeRequests(request->request
                        .antMatchers("/user/addUser", "/user/login","/stud/register").permitAll() // Allow public access to login and registration
                        .antMatchers("/student/**", "/enroll/**", "/course/**").hasRole("ADMIN") // Only ADMIN can access these endpoints
                        .antMatchers("/student/getAllStudents", "/enroll/enrollStudent").hasRole("STUDENT")
                        .anyRequest().authenticated())
                        .httpBasic(Customizer.withDefaults())
                        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();
        //.formLogin(Customizer.withDefaults())

    }
    @Bean
   public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider  provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
    @Bean
     public AuthenticationManager manager(AuthenticationConfiguration configuration) throws Exception {
       return configuration.getAuthenticationManager();
    }
}
