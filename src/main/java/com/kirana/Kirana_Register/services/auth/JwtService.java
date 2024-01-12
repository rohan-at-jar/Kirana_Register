package com.kirana.Kirana_Register.services.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Service class for handling JSON Web Tokens (JWT) related operations.
 */
@Component
public class JwtService {


    /**
     * Secret key used for signing and validating JWTs.
     */
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    /**
     * Extracts the username from the JWT token.
     *
     * @param token The JWT token.
     * @return The extracted username.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the expiration date from the JWT token.
     *
     * @param token The JWT token.
     * @return The extracted expiration date.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts a specific claim from the JWT token.
     *
     * @param token          The JWT token.
     * @param claimsResolver The function to resolve the desired claim.
     * @param <T>            The type of the claim.
     * @return The extracted claim value.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from the JWT token.
     *
     * @param token The JWT token.
     * @return The extracted claims.
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Checks if the JWT token is expired.
     *
     * @param token The JWT token.
     * @return True if the token is expired, false otherwise.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Validates a JWT token against a UserDetails object.
     *
     * @param token       The JWT token.
     * @param userDetails The UserDetails object.
     * @return True if the token is valid, false otherwise.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Generates a new JWT token for the given username.
     *
     * @param userName The username for which the token should be generated.
     * @return The generated JWT token.
     */
    public String generateToken(String userName){
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,userName);
    }

    /**
     * Creates a new JWT token with the specified claims and subject.
     *
     * @param claims   The claims to include in the token.
     * @param userName The subject of the token.
     * @return The created JWT token.
     */
    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    /**
     * Gets the secret key used for signing and validating JWTs.
     *
     * @return The secret key.
     */
    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}