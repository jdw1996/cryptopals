/*
 * Joseph Winters
 * XORDecryption class to represent a single-character XOR decryption
 * Spring 2016
 */


public class XORDecryption {

    private double score;
    private char key;
    private String plaintext;
    private String ciphertext;

    // Constructor
    public XORDecryption(double score, char key,
                         String plaintext, String ciphertext) {
        this.score = score;
        this.key = key;
        this.plaintext = plaintext;
        this.ciphertext = ciphertext;
    }

    // Getters and setters
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public char getKey() {
        return key;
    }
    public void setKey(char key) {
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
