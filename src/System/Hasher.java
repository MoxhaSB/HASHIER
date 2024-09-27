package System;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase que contiene los creadores de hash
 */
public class Hasher {


    /**
     * Constructor
     */
    Hasher(){}

    public String createHash_SHA_256(String contra) throws NoSuchAlgorithmException {

        MessageDigest crearHash = MessageDigest.getInstance("SHA-256");
        byte[] bytes = crearHash.digest(contra.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder(2 * bytes.length);

        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xff & aByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    public String createHash_SHA_512(String contra) throws NoSuchAlgorithmException {
        MessageDigest crearHash = MessageDigest.getInstance("SHA-512");
        byte[] bytes = crearHash.digest(contra.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder(2 * bytes.length);

        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xff & aByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    public String createHash_MD5(String contra) throws NoSuchAlgorithmException {
        MessageDigest crearHash = MessageDigest.getInstance("MD5");
        byte[] bytes = crearHash.digest(contra.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder(2 * bytes.length);

        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xff & aByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    public String createHash_SHA_1(String contra) throws NoSuchAlgorithmException {
        MessageDigest crearHash = MessageDigest.getInstance("SHA-1");
        byte[] bytes = crearHash.digest(contra.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder(2 * bytes.length);

        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xff & aByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
