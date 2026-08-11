package com.example.jobtracker.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

import java.util.Date;


class JwtServiceTest {


    private static final String SECRET =
            "test-secret-key-at-least-32-characters-long";


    private JwtService jwtService;



    @BeforeEach
    void setUp() {

        jwtService = new JwtService(SECRET, 3600000L);
    }



    @Test
    void shouldGenerateAndParseToken() {

        String token =
                jwtService.generateToken(1L, "john@test.com", "USER");


        assertNotNull(token);


        Claims claims =
                jwtService.parseToken(token);


        assertEquals(
                "1",
                claims.getSubject()
        );

        assertEquals(
                "john@test.com",
                claims.get("email", String.class)
        );

        assertEquals(
                "USER",
                claims.get("userRole", String.class)
        );
    }



    @Test
    void shouldIncludeExpirationInTheFuture() {

        String token =
                jwtService.generateToken(1L, "john@test.com", "USER");


        Claims claims =
                jwtService.parseToken(token);


        assertTrue(
                claims.getExpiration().after(new Date())
        );
    }



    @Test
    void shouldThrowWhenTokenIsExpired() {

        JwtService shortLivedJwtService =
                new JwtService(SECRET, -1000L);

        String expiredToken =
                shortLivedJwtService.generateToken(1L, "john@test.com", "USER");


        assertThrows(
                ExpiredJwtException.class,
                () -> shortLivedJwtService.parseToken(expiredToken)
        );
    }
}
