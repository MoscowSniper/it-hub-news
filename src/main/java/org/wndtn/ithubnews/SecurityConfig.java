package org.wndtn.ithubnews;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/admin/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/admin/login").permitAll()
                .defaultSuccessUrl("/admin", true)
                .and()
                .logout().logoutUrl("/admin/logout").logoutSuccessUrl("/")
                .and()
                .csrf().disable(); // для разработки можно отключить

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Встроенная учетная запись администратора
    @Bean
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService(PasswordEncoder encoder) {
        var user = org.springframework.security.core.userdetails.User
                .withUsername("admin")
                .password(encoder.encode("password"))
                .roles("ADMIN")
                .build();

        return new org.springframework.security.provisioning.InMemoryUserDetailsManager(user);
    }
}
