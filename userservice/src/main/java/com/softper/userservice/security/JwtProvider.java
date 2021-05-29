package com.softper.userservice.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    
    @Autowired
    private Environment env;


    //@Value("${env.jwtSecret}")
    private String jwtSecret= "fdsfdfokpokposdwkfposksd";

    //@Value("${env.jwtExpiration}")
    private int jwtExpiration = 200000;

    //@Value("${env.refreshTokenExpiration}")
    private int refreshTokenExpiration = 30000;

    public String generateJwtToken(Authentication authentication) {

        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getEmail()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .setId(UUID.randomUUID().toString())
                .compact();

    }

    /*
    public String generateRefreshToken(User user, String token, IRefreshTokenService refreshTokenService) throws Exception {

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setIssuedDate(new Date());
        refreshToken.setExpirationDate(new Date((new Date()).getTime() + refreshTokenExpiration));
        refreshToken.setToken(getJwtTokenId(token));
        refreshToken.setState(true);
        refreshTokenService.save(refreshToken);

        return refreshToken.getId();
    }

     */

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public String getJwtTokenId(String token) {
        String id ="";
        try{
            id = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getId();
        }catch (ExpiredJwtException e){
            id = e.getClaims().getId();
            logger.info(id);
        }
        return id;
    }

    /*
    public boolean validateRefreshToken(RefreshToken refreshToken) {

        if(!refreshToken.getState()){
            logger.error("RefreshToken is already used");
            return false;
        }

        if(refreshToken.getExpirationDate().before(new Date())){
            logger.error("RefreshToken has expired");
            return false;
        }
        return true;
    }

     */

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }
}