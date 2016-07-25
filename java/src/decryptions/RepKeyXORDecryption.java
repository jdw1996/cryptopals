/*
 * Joseph Winters
 * RepKeyXORDecryption class to represent a repeating key XOR decryption
 * Spring 2016
 */


public class RepKeyXORDecryption extends Decryption {

    // Constructor
    public RepKeyXORDecryption(String key,
                               String plaintext,
                               Data ciphertext) {
        super(plaintext, ciphertext);
        this.key = key;
    }

    // Getters and setters
    public String getKey() {
        return key;
    }

    private String key;

}
