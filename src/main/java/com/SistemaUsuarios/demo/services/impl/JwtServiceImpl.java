package com.SistemaUsuarios.demo.services.impl;

import com.SistemaUsuarios.demo.services.IJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements IJwtService {
    // por valores dos cosas
    // desde el lado properties
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expiration;


    @Override
    public String generarToken(UserDetails userDetails) {
        return generarToken(new HashMap<>(),userDetails);
    }

    public String generarToken(Map<String,Object> extraClaims,UserDetails userDetails){
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public boolean esTokenValido(String token, UserDetails userDetails) {
        final String username = extraerUsername(token);
        return username.equals(userDetails.getUsername()) && !estokenExpirado(token);
    }

    @Override
    public String extraerUsername(String token) {
        return extraerClaims(token,Claims::getSubject);
    }

    @Override
    public Date extraerExpiracion(String token) {
        return extraerClaims(token,Claims::getExpiration);
    }

    public <T> T extraerClaims(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extraerTodoClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public boolean estokenExpirado(String token) {
        return extraerExpiracion(token).before(new Date());
    }

    //1234 // hagahhqhwqiu1231213sshshsah

    @Override
    public Claims extraerTodoClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
