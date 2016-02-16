package io.farragolabs.bugbuster;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

public class JWT {

    private static final String server_secret = "bugbuster";

    public static String sign(String to_be_encoded) {
        Base64 decoder = new Base64(true);
        byte[] encode = decoder.encodeBase64(server_secret.getBytes());
        JWTSigner jwtSigner = new JWTSigner(encode);
        Map<String, Object> claims = new HashMap<>();
        claims.put("aud", to_be_encoded);
        return jwtSigner.sign(claims);
    }

    public static boolean verify(String verification_code) {
        Base64 decoder = new Base64(true);
        byte[] secret = decoder.encodeBase64(server_secret.getBytes());

        Map<String, Object> decodedPayload;
        try {
            decodedPayload = new JWTVerifier(secret)
                    .verify(verification_code);

            return decodedPayload.size() > 0;
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | IOException | JWTVerifyException e) {
            e.printStackTrace();
        }

        return false;
    }
}
