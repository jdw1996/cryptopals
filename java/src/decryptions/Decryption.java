/**********************************************
 Joseph Winters
 Abstract Decryption class to hold decryptions
 Spring 2016
**********************************************/

public abstract class Decryption {

    private String plaintext;
    private Data ciphertext;

    // Constructor
    public Decryption(String plaintext, Data ciphertext) {
        this.plaintext = plaintext;
        this.ciphertext = ciphertext;
    }

    // Getters
    public String getPlaintext() {
        return plaintext;
    }
    public Data getCiphertext() {
        return ciphertext;
    }

}
