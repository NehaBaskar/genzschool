package com.sample.genzschool.Config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    //Permits all requests in web application
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
       http.csrf((csrf) -> csrf.ignoringRequestMatchers("/saveForm").ignoringRequestMatchers("/public/**")
               .ignoringRequestMatchers("/api/**").ignoringRequestMatchers("/data-api/**"))
               .authorizeHttpRequests((requests) -> requests.requestMatchers("/dashboard").authenticated()
               .requestMatchers("/displayMessages/**").hasRole("ADMIN")
               .requestMatchers("/admin/**").hasRole("ADMIN")
               .requestMatchers("/closeMsg/**").hasRole("ADMIN")
               .requestMatchers("/api/**").authenticated()
               .requestMatchers("/data-api/**").authenticated()
               .requestMatchers("/displayProfile").authenticated()
               .requestMatchers("/updateProfile").authenticated()
               .requestMatchers("/student/**").hasRole("STUDENT")
               .requestMatchers("/","/home").permitAll()
               .requestMatchers("/holiday/**").permitAll()
               .requestMatchers("/profile/**").permitAll()
               /*.requestMatchers("/data-api/**").permitAll()
               .requestMatchers("/courseses/**").permitAll()
               .requestMatchers("/contacts/**").permitAll()*/
               .requestMatchers("/contact").permitAll()
               .requestMatchers("/saveForm").permitAll()
               .requestMatchers("/courses").permitAll()
               .requestMatchers("/about").permitAll()
               .requestMatchers("/login").permitAll()
               .requestMatchers("/logout").permitAll()
               .requestMatchers("/public/**").permitAll()
               .requestMatchers("/assets/**").permitAll())
               .formLogin((FormLoginConfigurer) -> FormLoginConfigurer.loginPage("/login").defaultSuccessUrl("/dashboard")
                    .failureUrl("/login?error=true").permitAll())
               .logout((LogoutConfigurer) -> LogoutConfigurer.logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll())
               .httpBasic(Customizer.withDefaults());

       
        return http.build();
    }

   /* @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("12345").roles("ADMIN").build();
        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("54321").roles("USER").build();
        return new InMemoryUserDetailsManager(user,admin);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
