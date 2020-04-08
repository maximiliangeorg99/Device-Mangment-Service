package c24.thriftshop.stripe.demo.domain.user;

import c24.thriftshop.stripe.demo.util.Converter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class Password {
    //hash und salt als String
    private final String hash;
    private final String salt;
    MessageDigest messageDigest;

    public Password(final String password) {
        final byte[] saltBytes = new byte[20];
        new SecureRandom().nextBytes(saltBytes);
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        salt = new Converter().bytesToHex(saltBytes);
        final String StringToHash = password + salt;
        final byte[] hashBytes = messageDigest.digest(StringToHash.getBytes(StandardCharsets.UTF_8));
        hash = new Converter().bytesToHex(hashBytes);
    }

    public String getHash() {
        return hash;
    }
}
