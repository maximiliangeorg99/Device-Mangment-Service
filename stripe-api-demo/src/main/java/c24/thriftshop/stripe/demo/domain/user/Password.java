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
        this.hash = password;
        this.salt = password.substring(password.length() - 40);
    }

    public Password(final String password, final String salt) {
        String hash1;
        this.salt = salt;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        final String StringToHash = password;
        final byte[] hashBytes = messageDigest.digest(StringToHash.getBytes(StandardCharsets.UTF_8));
        hash1 = new Converter().bytesToHex(hashBytes);
        hash1 = hash1 + salt;
        hash = hash1;
    }

    public static String generateRandomSalt() {
        final byte[] saltBytes = new byte[20];
        new SecureRandom().nextBytes(saltBytes);
        return new Converter().bytesToHex(saltBytes);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Password password = (Password) o;

        return hash != null ? hash.equals(password.hash) : password.hash == null;
    }

    @Override
    public int hashCode() {
        return hash != null ? hash.hashCode() : 0;
    }

    public String getSalt() {
        return salt;
    }

    public String getHash() {
        return hash;
    }
}
