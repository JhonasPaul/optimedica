package com.optimedica.security.jwt;

import com.optimedica.security.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    //  Esta será nuestra clave secreta para firmar los tokens
    @Value("${jwt.secret}")
    private String secretKey;

    //  Objeto clave usado para firmar/verificar el JWT
    private Key key;

    //  Duración del token en milisegundos (por defecto 1 hora)
    private final long jwtExpirationMs = 3600000;

    //  Método que se ejecuta después de la inyección de dependencias para construir la clave segura
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    //  Genera un JWT firmado con los datos del usuario
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail()) // identificador del usuario
                .setIssuedAt(new Date()) // fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) // expiración
                .signWith(key, SignatureAlgorithm.HS256) // firma con algoritmo HS256 y clave secreta
                .compact();
    }

    //  Extrae el email (subject) desde un token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //  Verifica si el token es válido para el usuario dado
    public boolean isTokenValid(String token, User user) {
        final String username = extractUsername(token);
        return (username != null && username.equals(user.getUsername())) && !isTokenExpired(token);    }

    //  Verifica si el token ya expiró
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //  Extrae la fecha de expiración
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //  Método genérico para extraer cualquier dato del token
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = parseToken(token);
        return claimsResolver.apply(claims);
    }

    //  Parsea el token y obtiene los claims (datos firmados)
    private Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
