package System;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hasher class
 */
public class Hasher {


    /**
     * Constructor
     */
    Hasher(){}

    /**
     * Method that create a hash with format sha-256
     * @param contra the word to create the hash
     * @return the hash
     * @throws NoSuchAlgorithmException exception
     */
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

    /**
     * Method that create a hash with format sha-512
     * @param contra the word to create the hash
     * @return the hash
     * @throws NoSuchAlgorithmException exception
     */
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

    /**
     * Method that create a hash with format MD5
     * @param contra the cord to create the hash
     * @return the hash
     * @throws NoSuchAlgorithmException exception
     */
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

    /**
     * Method that create a hash with format sha-1
     * @param contra the word to create the hash
     * @return the hash
     * @throws NoSuchAlgorithmException exception
     */
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
