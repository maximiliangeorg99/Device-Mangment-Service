package c24.thriftshop.stripe.demo.domain.user;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class Password {
    //hash und salt als String
    private final byte[] hash;
    private final byte[] salt;
    MessageDigest messageDigest;

    public Password(final String password) {
        salt = new byte[20];
        new SecureRandom().nextBytes(salt);
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        final String SaltString = new String(salt, StandardCharsets.UTF_8);
        final String StringToHash = password + SaltString;
        hash = messageDigest.digest(StringToHash.getBytes(StandardCharsets.UTF_8));
    }

    public byte[] getHash() {
        return hash;
    }
}
