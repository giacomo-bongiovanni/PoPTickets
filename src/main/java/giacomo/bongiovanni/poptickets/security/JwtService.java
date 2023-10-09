package giacomo.bongiovanni.poptickets.security;

import giacomo.bongiovanni.poptickets.dto.mapper.UserMapper;
import giacomo.bongiovanni.poptickets.model.User;
import giacomo.bongiovanni.poptickets.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final UserService userService;
    public JwtService(UserService userService) {
        this.userService = userService;
    }

    @Value("${jwt.secret}")
    private String key;


    private Key generateKey(){
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    private Claims createPayloadToken(User u){
        String role = u.getRole().toString();
        String username = u.getEmail();
        Claims claims = Jwts.claims().add("role",role).subject(username).build();
        return claims;
    }


    public String createToken(User u){
        Claims c= createPayloadToken(u);
        long expiration = 1000L*60*60*24*60;
        String token = Jwts.builder()
                .setClaims(c)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(generateKey())
                .compact();
        return token;
    }

    private Claims extractClaim(String token){
        Claims c=Jwts.parser()
                .setSigningKey(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return c;
    }
    public String extractSubject(String token){
        return extractClaim(token).getSubject();
    }
    public User extractUser(String token){
        String username = extractSubject(token);
        return UserMapper.INSTANCE.userDTOToUser(userService.findByEmail(username));
    }
    private boolean isTokenExpired(String token) {
        Date expirationDate =extractClaim(token).getExpiration();
        return expirationDate.before(new Date());
    }
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractSubject(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
