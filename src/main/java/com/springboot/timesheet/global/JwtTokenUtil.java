package com.springboot.timesheet.global;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil {
    // Token Header
    public static final String TOKEN_HEADER = "Authorization";
    // Token prefix
    public static final String TOKEN_PREFIX = "Bearer ";
    // Theme
    public static final String SUBJECT = "timesheet";
    // Expirtion Time
    public static final long EXPIRITION = 1000 * 24 * 60 * 60 * 7;
    // key
    public static final String SECRET_KEY = "timesheet_secret";
    // role
    private static final String ROLE_CLAIMS = "role";
    /**
     * generate Token
     */
    public static String createToken(String username,String role) {
        Map<String,Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, role);
        
        String token = Jwts
                .builder()
                .setSubject(username)
                .setClaims(map)
                .claim("username",username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
        return token;
    }

    /**
     * decode Token
     */
    public static Claims checkJWT(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get username
     */
    public static String getUsername(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }

    /**
     * get role
     */
    public static String getUserRole(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("role").toString();
    }

    /**
     * check expiration
     */
    public static boolean isExpiration(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }
}
