/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package rizkydwisaputra.jwtgenerator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * instagram @rizky_simas
 * github https://github.com/ketikrizky
 * youtube https://www.youtube.com/@rizkydwisaputra0212
 * @author ideapad330
 */
public class JWTgenerator {
    private static final String SECRET_KEY = "9cf95dacd226dcf43da376cdb6cbba7035218921"; // Gantilah dengan kunci rahasia Anda

    public static String createJWT(String username, String password, long expirationMillis) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationMillis);

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("password", password);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    public static Map<String, Object> decodeJWT(String jwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwt)
                    .getBody();

            Map<String, Object> decodedClaims = new HashMap<>();
            decodedClaims.put("username", claims.get("username", String.class));
            decodedClaims.put("password", claims.get("password", String.class));

            return decodedClaims;
        } catch (Exception e) {
            // Token tidak valid
            return null;
        }
    }


    public static void main(String[] args) {
        String username = "jhon doe";
        String password = "12password";
        long expirationMillis = 3600000; // time for 1 hours

        String jwtToken = createJWT(username, password, expirationMillis);
        System.out.println("JWT Token: " + jwtToken);

        Map<String, Object> decodedClaims = decodeJWT(jwtToken);
        if (decodedClaims != null) {
            System.out.println("this decode token");
            System.out.println("Username: " + decodedClaims.get("username"));
            System.out.println("Password: " + decodedClaims.get("password"));
        } else {
            System.out.println("Token tidak valid.");
        }
    }
}
