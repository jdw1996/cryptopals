/*
 * Joseph Winters
 * XORDecryption class to represent a single-character XOR decryption
 * Spring 2016
 */


public class XORDecryption extends Decryption {

    // Constructor
    public XORDecryption(double score, char key,
                         String plaintext, String ciphertext) {
        super(plaintext, ciphertext);
        this.score = score;
        this.key = key;
    }

    // Getters and setters
    public double getScore() {
        return score;
    }
    public char getKey() {
        return key;
    }

    private double score;
    private char key;

}
