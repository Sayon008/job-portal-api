package org.vibe.jobportal.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Secret key for signing the JWT
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Token validity period (e.g., 10 hours)
    private final long jwtExpirationInMins = 36000000;   // 10 hours


    // Generate JWT Token
    public String generateToken(String email){
        Date now = new Date();

        Date expiryDate = new Date(now.getTime() + jwtExpirationInMins);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey)
                .compact();

    }

    /**
     * Validates the JWT token.
     *
     * @param token the JWT token
     * @return true if valid, false otherwise
     */
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        }
        catch(JwtException | IllegalArgumentException exception){
            return false;
        }
    }

    /**
     * Extracts the email (subject) from the JWT token.
     *
     * @param token the JWT token
     * @return the email
     */
    public String extractEmailFromToken(String token){
        Claims claim = Jwts.parser().
                setSigningKey(secretKey).
                build().parseClaimsJws(token).
                getBody();

        return claim.getSubject();
    }
}






//Key Generation: We use Keys.secretKeyFor(SignatureAlgorithm.HS256) to generate a secure key for signing the JWT.
//
//Token Generation: The generateToken method creates a JWT with the user's email as the subject, sets the issue and expiration dates, and signs it.
//
//Token Validation: The validateToken method checks if the token is valid and not expired.
//
//Extract Email: The getEmailFromToken method retrieves the email (subject) from the token.