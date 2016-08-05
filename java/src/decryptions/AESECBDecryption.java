/***************************************************************
 Joseph Winters
 AESECBDecryption class to represent an ECB-mode AES decryption
 Spring 2016
***************************************************************/


public class AESECBDecryption extends Decryption {

    // Constructor
    public AESECBDecryption(String key, String plaintext, Data ciphertext) {
        super(plaintext, ciphertext);
        this.key = key;
    }

    // Getters
    public String getKey() {
        return key;
    }

    private String key;

}
