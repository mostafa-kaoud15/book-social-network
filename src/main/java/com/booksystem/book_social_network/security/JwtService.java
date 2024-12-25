package com.booksystem.book_social_network.security;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.booksystem.book_social_network.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${application.jwt.SECRET}")
    private String SECRET;
    @Value("${application.jwt.EXPIRATION}")
    private Long EXPIRATION;

    public String generateJwt(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("authorities", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .setIssuedAt(new Date())
                .signWith(signKey())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .compact();
    }

    public boolean isTokenValid(String token, User user) {
        if (isTokenExpired(token) && !extractUsername(token).equals(user.getUsername())) {
            return false;
        }
        return true;
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);

    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaims(String token, Function<Claims, T> func) {
        Claims claims = extractAllClaims(token);
        return func.apply(claims);

    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signKey())
                .build()
                .parseClaimsJws(token).getBody();
    }

    private Key signKey() {
        byte[] keyByts = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyByts);
    }
}
