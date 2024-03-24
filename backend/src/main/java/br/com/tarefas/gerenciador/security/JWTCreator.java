package br.com.tarefas.gerenciador.security;

import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

public class JWTCreator {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String ROLES_AUTHORITIES = "authorities";

    static protected SecretKey getSecretKey(String key) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
        return secretKey;
    }

    public static String create(String prefix, String key, JWTObject jwtObject) {
        String token = Jwts.builder()
            .subject(jwtObject.getSubject())
            .issuedAt(jwtObject.getIssuedAt())
            .expiration(jwtObject.getExpiration())
            .claim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRoles()))
            .signWith(JWTCreator.getSecretKey(key)).compact();

        return prefix + " " + token;
    }

    @SuppressWarnings("unchecked")
    public static JWTObject create(String token, String prefix, String key)
            throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException {
        
        final Claims claims = Jwts.parser()
            .verifyWith(JWTCreator.getSecretKey(key))
            .build()
            .parseSignedClaims(token.replace(prefix, ""))
            .getPayload();
            
        JWTObject jwtDTO = new JWTObject();
        jwtDTO.setSubject(claims.getSubject());
        jwtDTO.setExpiration(claims.getExpiration());
        jwtDTO.setIssuedAt(claims.getIssuedAt());
        jwtDTO.setRoles((List<String>) claims.get(ROLES_AUTHORITIES));

        return jwtDTO;
    }

    private static List<String> checkRoles(List<String> roles) {
        return roles.stream().map(s -> "ROLE_".concat(s.replaceAll("ROLE_",""))).collect(Collectors.toList());
    }

}