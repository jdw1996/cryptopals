/*
 * Joseph Winters
 * RepKeyXORDecryption class to represent a repeating key XOR decryption
 * Spring 2016
 */


public class RepKeyXORDecryption {

    private String key;
    private String plaintext;
    private String ciphertext;

    // Constructor
    public RepKeyXORDecryption(String key,
                               String plaintext,
                               String ciphertext) {
        this.key = key;
        this.plaintext = plaintext;
        this.ciphertext = ciphertext;
    }

    // Getters and setters
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getPlaintext() {
        return plaintext;
    }
    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }
    public String getCiphertext() {
        return ciphertext;
    }
    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

}
