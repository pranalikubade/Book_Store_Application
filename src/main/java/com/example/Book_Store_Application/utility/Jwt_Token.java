package com.example.Book_Store_Application.utility;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

@Component
public class Jwt_Token {
    private static final String SECRET = "Pranali";
    public static String createToken(long id) {
        String token;
        token = JWT.create()
                .withClaim("id", id)
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    public static long decodeToken(String token) {
        long id = 0;
        if (token != null) {
            id = JWT.require(Algorithm.HMAC256(SECRET)).
                    build().verify(token).
                    getClaim("id").asLong();
        }
        return id;
    }
}

