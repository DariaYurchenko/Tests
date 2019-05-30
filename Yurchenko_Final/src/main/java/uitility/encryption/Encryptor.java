package uitility.encryption;

import exception.EncryptionException;
import org.apache.log4j.Logger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class Encryptor {
    private static final Logger LOGGER = Logger.getLogger(Encryptor.class);
    private static final String SHA_1 = "SHA-1";

    public static boolean verifyPassword(String passwordToCheck, String hash, byte[] salt) {
        String hashToCheck = getSecurePassword(passwordToCheck, salt);
        return hash.equalsIgnoreCase(hashToCheck);
    }

    public static String getSecurePassword(String password, byte[] salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA_1);
            messageDigest.update(salt);
            byte[] digest = messageDigest.digest(password.getBytes());
            return createStringPassword(digest);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.fatal("No such algorithm (getting secure password).");
            throw new EncryptionException(e);
        }
    }

    public static byte[] getSalt() {
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            return salt;
        } catch (NoSuchAlgorithmException e) {
            LOGGER.fatal("No such algorithm (getting salt).");
            throw new EncryptionException(e);
        }
    }

    private static String createStringPassword(byte[] arr) {
        StringBuilder builder = new StringBuilder();
        for (byte b : arr) {
            builder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return builder.toString();
    }
}
