/*
 * Joseph Winters
 * Cryptopals Set 1 Challenge 5 Functions
 * Spring 2016
 */


public class Set1Challenge5 {

    // Return the hex string created by encrypting ASCII string plaintext with
    //   key under repeating XOR.
    public static String encryptWithRepKeyXOR(String key, String plaintext) {
        String repString = getRepString(plaintext.length(), key);

        Set1Challenge1.Data repStringData
            = new Set1Challenge1.Data(repString,
                                      Set1Challenge1.Encoding.ASCII);
        String repStringHex = repStringData.getHex();
        Set1Challenge1.Data plaintextData
            = new Set1Challenge1.Data(plaintext,
                                      Set1Challenge1.Encoding.ASCII);
        String plaintextHex = plaintextData.getHex();

        String ciphertext = Set1Challenge2.fixedXOR(repStringHex, plaintextHex);
        return ciphertext;
    }

    // Return the ASCII string created by decrypting hex string ciphertext with
    //   key under repeating XOR.
    public static String decryptWithRepKeyXOR(String key, String ciphertext) {
        String repString = getRepString(ciphertext.length() / 2, key);

        Set1Challenge1.Data repStringData
            = new Set1Challenge1.Data(repString,
                                      Set1Challenge1.Encoding.ASCII);
        String repStringHex = repStringData.getHex();

        String plaintextHex = Set1Challenge2.fixedXOR(repStringHex, ciphertext);

        Set1Challenge1.Data plaintextData
            = new Set1Challenge1.Data(plaintextHex,
                                      Set1Challenge1.Encoding.HEX);
        String plaintext = plaintextData.getASCII();
        return plaintext;
    }

    // Return a string of length len consisting of repetitions of key.
    private static String getRepString(int len, String key) {
        StringBuilder repStringBuilder
            = new StringBuilder(len + key.length() - 1);
        while (repStringBuilder.length() < len) {
            repStringBuilder.append(key);
        }
        repStringBuilder.setLength(len);
        return repStringBuilder.toString();
    }

}
