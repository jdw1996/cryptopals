/*
 * Joseph Winters
 * Cryptopals Set 1 Challenge 3 Functions
 * Spring 2016
 */


import java.util.ArrayList;
import java.util.List;


public class Set1Challenge3 {

    // Return an XORDecryption containing the score of the best decryption, the
    //   character used for decryption, and the decrypted version of the hex
    //   string ciphertext.
    public static XORDecryption crackSingleCharXOR(String ciphertext) {
        char chr0 = Set1Challenge1.chr(0);
        char chr127 = Set1Challenge1.chr(127);
        List<XORDecryption> xorDecryptions = new ArrayList<XORDecryption>();
        for (char possibleKey = chr0; possibleKey <= chr127; possibleKey++) {
            xorDecryptions.add(decryptWith(possibleKey, ciphertext));
        }
        XORDecryption bestXORDecryption = xorDecryptions.get(0);
        XORDecryption currXORDecryption;
        for (int i = 1; i < xorDecryptions.size(); i++) {
            currXORDecryption = xorDecryptions.get(i);
            if (currXORDecryption.getScore() > bestXORDecryption.getScore()) {
                bestXORDecryption = currXORDecryption;
            }
        }
        return bestXORDecryption;
    }

    public static class XORDecryption {

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

    // Return an XORDecryption with key key, decryption determined by decrypting
    //   the hex string ciphertext with key, and score determined by scoring the
    //   decryption on how likely it is to be English text.
    private static XORDecryption decryptWith(char key, String ciphertext) {
        int halfCiphertextLength = ciphertext.length() / 2;
        StringBuffer repStringBuffer = new StringBuffer(halfCiphertextLength);
        for (int i = 0; i < halfCiphertextLength; i++) {
            repStringBuffer.append(key);
        }
        String repString = repStringBuffer.toString();
        Set1Challenge1.Data repStringData
            = new Set1Challenge1.Data(repString,
                                      Set1Challenge1.Encoding.ASCII);
        String repStringHex = repStringData.getHex();

        String plaintextHex
            = Set1Challenge2.fixedXOR(repStringHex, ciphertext);
        Set1Challenge1.Data plaintextData
            = new Set1Challenge1.Data(plaintextHex,
                                      Set1Challenge1.Encoding.HEX);
        String plaintext = plaintextData.getASCII();
        double score = scoreIsEnglish(plaintext);

        return new XORDecryption(score, key, plaintext, ciphertext);
    }

    // Return true if c is a printable ASCII character and false otherwise.
    private static boolean isPrintableASCII(char c) {
        return ' ' <= c && c < '~';
    }

    // Return true if c is an ASCII letter character and false otherwise.
    private static boolean isASCIILetter(char c) {
        return ('a' <= c && c < 'z') || ('A' <= c && c <= 'Z');
    }

    // Score how likely it is that the string plaintext is English text.
    private static double scoreIsEnglish(String plaintext) {
        double numPrintableASCII = 0;
        double numASCIILetters = 0;
        for (int i = 0; i < plaintext.length(); i++) {
            if (isPrintableASCII(plaintext.charAt(i))) {
                numPrintableASCII++;
            }
            if (isASCIILetter(plaintext.charAt(i))) {
                numASCIILetters++;
            }
        }
        return (numPrintableASCII + numASCIILetters) / plaintext.length();
    }

}
