/*
 * Joseph Winters
 * Cryptopals Set 1 Challenge 5 Functions
 * Spring 2016
 */


public class Set1Challenge5 {

    // Return the hex string created by encrypting ASCII string plaintext with
    //   key under repeating XOR.
    public static Data encryptWithRepKeyXOR(String key, String plaintext) {
        String repString = getRepString(plaintext.length(), key);
        Data ciphertext
            = Set1Challenge2.fixedXOR(repString, Data.Encoding.ASCII,
                                      plaintext, Data.Encoding.ASCII);
        return ciphertext;
    }

    // Return the ASCII string created by decrypting encoding encoded string
    //   ciphertext with key under repeating XOR.
    public static String decryptWithRepKeyXOR(String key, String ciphertext,
                                              Data.Encoding encoding) {
        String repString = getRepString(ciphertext.length() / 2, key);
        Data plaintextData
            = Set1Challenge2.fixedXOR(repString, Data.Encoding.ASCII,
                                      ciphertext, encoding);
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
