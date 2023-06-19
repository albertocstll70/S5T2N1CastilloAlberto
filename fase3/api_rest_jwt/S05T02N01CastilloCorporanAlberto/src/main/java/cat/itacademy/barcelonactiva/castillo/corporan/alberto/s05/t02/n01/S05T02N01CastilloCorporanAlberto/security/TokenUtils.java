package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.security;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.exception.InvalidJwtAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenUtils {

    private  String accessTokenSecret;
    private static final Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;

    private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    public TokenUtils(@Value("${security.jwt.token.secret-key}") String secret) {
        accessTokenSecret = secret;
    }

    public String createToken(String nombre) {
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);


       

        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", nombre);

        String token =  Jwts.builder()
                .setSubject(nombre)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(accessTokenSecret.getBytes()), SignatureAlgorithm.HS256)
                .compact();

                logger.info("Token generado: " + token);

                return token;

    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {


            

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(accessTokenSecret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String nombre = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(nombre, null, Collections.emptyList());

        } catch (JwtException e) {
            logger.error("Error al autenticar el token", e);
            throw new InvalidJwtAuthenticationException("Falló la autenticación del JWT");
        }
    }
}
