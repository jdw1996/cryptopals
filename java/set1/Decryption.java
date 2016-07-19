/*
 * Joseph Winters
 * Abstract Decryption class to hold decryptions
 * Spring 2016
 */

public abstract class Decryption {

    private String plaintext;
    private String ciphertext;

    // Constructor
    public Decryption(String plaintext, String ciphertext) {
        this.plaintext = plaintext;
        this.ciphertext = ciphertext;
    }

    // Getters
    public String getPlaintext() {
        return plaintext;
    }
    public String getCiphertext() {
        return ciphertext;
    }

}
