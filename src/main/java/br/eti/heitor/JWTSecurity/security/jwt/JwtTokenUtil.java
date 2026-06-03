package br.eti.heitor.JWTSecurity.security.jwt;

import br.eti.heitor.JWTSecurity.security.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author sesi3dia
 */
@Component
public class JwtTokenUtil {

    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;
    // 24 horas

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    public String generateAcessToken(User user) {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .subject(String.format("%s,%s", user.getId(), user.getEmail()))
                .claim("roles", user.getRoles())
                .issuer("ProfKGe")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(secretKey)
                .compact();
    }

    public boolean validateAccessToken(String token) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
            SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);

            // Caso falhar é lançada uma exception.
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);

            return true;

        } catch (ExpiredJwtException ex) {
            LOGGER.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported", ex);
        } catch (io.jsonwebtoken.JwtException ex) {
            LOGGER.error("Signature validation failed");
        }
        return false;

    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    public String getRoles(String token) {
        return (String) parseClaims(token).get("roles");
    }

    private Claims parseClaims(String token) {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.parser()
                .verifyWith(secretKey).build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
