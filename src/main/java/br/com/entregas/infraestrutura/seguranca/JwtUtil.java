package br.com.entregas.infraestrutura.seguranca;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import java.util.List;

@Component
public class JwtUtil 
{
    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.expiration}")
    private long expirationMillis;

    private Key getSigningKey() 
    {
        return Keys.hmacShaKeyFor( SECRET.getBytes( ) );
    }

    public String extractUsername( String token ) 
    {
        return extractClaim( token, Claims::getSubject );
    }

    public <T> T extractClaim( String token, Function<Claims, T> claimsResolver ) 
    {
        final Claims claims = extractAllClaims( token );
        
        return claimsResolver.apply( claims );
    }

    private Claims extractAllClaims( String token ) 
    {
        return Jwts.parser()
                .setSigningKey( getSigningKey( ) )
                .build()
                .parseClaimsJws( token )
                .getBody();
    }

    public boolean validateToken( String token, String username ) 
    {
        final String extractedUsername = extractUsername( token );
        
        return ( extractedUsername.equals( username ) && !isTokenExpired( token ) );
    }

    private boolean isTokenExpired( String token ) 
    {
        return extractClaim( token, Claims::getExpiration ).before( new Date() );
    }

    public List<GrantedAuthority> getAuthorities( String token ) 
    {
        return Collections.singletonList( new SimpleGrantedAuthority( "ROLE_USER" ) );
    }

    public String generateToken( String username ) 
    {
        return Jwts.builder()
            .setSubject( username )
            .setIssuedAt( new Date() )
            .setExpiration( new Date( System.currentTimeMillis() + expirationMillis ) )
            .signWith( getSigningKey() )
            .compact();
    }
}