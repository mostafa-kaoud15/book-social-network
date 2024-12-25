package com.booksystem.book_social_network.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.booksystem.book_social_network.user.User;
import com.booksystem.book_social_network.user.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepo;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authenticationHeader = request.getHeader("Authorization");
        if (authenticationHeader == null) {
            filterChain.doFilter(request, response);
            return;
        }
        if (!authenticationHeader.startsWith("Bearer ")
                || SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String token = authenticationHeader.substring(7);
            // token => username, roles,
            String email = jwtService.extractUsername(token);

            User user = userRepo.getByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
            if (email == null && jwtService.isTokenValid(token, user)) {
                filterChain.doFilter(request, response);
                return;
            }
            SecurityContextHolder.getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
            filterChain.doFilter(request, response);

        } catch (RuntimeException ex) {
            handlerExceptionResolver.resolveException(request, response, null, ex);
            
        }
    }

}
