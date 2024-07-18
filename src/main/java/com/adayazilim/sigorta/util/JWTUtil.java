package com.adayazilim.sigorta.util;

import com.adayazilim.sigorta.entity.Role;
import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import static com.adayazilim.sigorta.security.AuthConstants.SECRET_KEY;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
public class JWTUtil implements Serializable {

    @Serial
    private static final long serialVersionUID = -2550185165626007488L;

    @Value("${jwt.accessToken.expirationMs}")
    private Long accessTokenExpirationMs;

//    public String generateToken(UserDetails userDetails) {
//        Optional<String> role = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .map(authority -> authority.replace("ROLE_", ""))  // "ROLE_" önekini kaldır
//                .findFirst();
//
//        // Rol bulunamazsa hata fırlat
//        if (!role.isPresent()) {
//            throw new IllegalArgumentException("User has no roles");
//        }
//
//        // Role enumunu bul
//        Role userRole;
//        try {
//            userRole = Role.valueOf(role.get());
//        } catch (IllegalArgumentException e) {
//            throw new IllegalArgumentException("Invalid role: " + role.get(), e);
//        }
//
//        return doGenerateToken(userRole.name(), userDetails.getUsername(), accessTokenExpirationMs);
//    }

    public String generateToken(UserDetails userDetails) {
        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User has no roles"));

        return doGenerateToken(role, userDetails.getUsername(), accessTokenExpirationMs);
    }

    private String doGenerateToken(String claim, String subject, Long expirationMs) {
        try {
            return JWT.create()
                    .withSubject(subject)
                    .withExpiresAt(new Date(System.currentTimeMillis() + expirationMs))
                    .withClaim("role", claim)
                    .sign(HMAC512(SECRET_KEY));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
