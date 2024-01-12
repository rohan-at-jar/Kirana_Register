package com.kirana.Kirana_Register.filter;

import com.kirana.Kirana_Register.config.RateLimitConfig;
import com.kirana.Kirana_Register.services.auth.JwtService;
import com.kirana.Kirana_Register.services.auth.UserInfoUserDetailsService;
import io.github.bucket4j.Bucket;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoUserDetailsService userDetailsService;

    @Autowired
    private final RateLimitConfig rateLimitConfig;

    public JwtAuthFilter(JwtService jwtService, UserInfoUserDetailsService userDetailsService, RateLimitConfig rateLimitConfig) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.rateLimitConfig = rateLimitConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
                Bucket bucket = rateLimitConfig.resolveBucket("SER");

                if (bucket.tryConsume(1)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    response.setStatus(429);
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}