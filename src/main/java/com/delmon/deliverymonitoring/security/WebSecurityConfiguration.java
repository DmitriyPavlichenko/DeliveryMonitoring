package com.delmon.deliverymonitoring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.delmon.deliverymonitoring.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "css/*", "js/*").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails testAdmin = User.builder()
                .username("manager")
                .password(passwordEncoder.encode("pass"))
                .authorities(MANAGER.getGrantedAuthorities())
                .build();

        UserDetails testSupplier = User.builder()
                .username("supplier")
                .password(passwordEncoder.encode("pass"))
                .authorities(PRODUCT_SUPPLIER.getGrantedAuthorities())
                .build();

        UserDetails testWorker = User.builder()
                .username("worker")
                .password(passwordEncoder.encode("pass"))
                .authorities(WAREHOUSE_WORKER.getGrantedAuthorities())
                .build();


        return new InMemoryUserDetailsManager(testAdmin, testSupplier, testWorker);
    }
}
