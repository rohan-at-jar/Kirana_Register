package com.kirana.Kirana_Register.config;
import com.kirana.Kirana_Register.filter.JwtAuthFilter;
import com.kirana.Kirana_Register.services.auth.UserInfoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class for Spring Security in Kirana Register application.
 * Configures authentication, authorization, JWT authentication filter, and session management.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Autowired JWT authentication filter.
     */
    @Autowired
    private JwtAuthFilter authFilter;

    /**
     * Bean for configuring user details service.
     *
     * @return UserDetailsService implementation.
     */
    @Bean
    //authentication
    public UserDetailsService userDetailsService() {

        return new UserInfoUserDetailsService();
    }

    /**
     * Configures the security filter chain.
     *
     * @param http HttpSecurity instance to configure.
     * @return SecurityFilterChain instance.
     * @throws Exception If configuration encounters an exception.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/sign-up", "/login").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/**")
                .authenticated().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Bean for configuring password encoder.
     *
     * @return PasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean for configuring authentication provider.
     *
     * @return AuthenticationProvider instance.
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Bean for configuring AuthenticationManager.
     *
     * @param config AuthenticationConfiguration instance.
     * @return AuthenticationManager instance.
     * @throws Exception If configuration encounters an exception.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}