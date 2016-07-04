/*
 * Joseph Winters
 * Cryptopals Set 1 Challenge 5 Functions
 * Spring 2016
 */


public class Set1Challenge5 {

    // Return the hex string created by encrypting plaintext with key under
    //   repeating XOR.
    public static String encryptWithRepKey(String key, String plaintext) {
        StringBuilder repStringBuilder
            = new StringBuilder(plaintext.length() + key.length() - 1);
        while (repStringBuilder.length()  < plaintext.length()) {
            repStringBuilder.append(key);
        }
        repStringBuilder.setLength(plaintext.length());
        String repString = repStringBuilder.toString();

        Set1Challenge1.Data repStringData
            = new Set1Challenge1.Data(repString,
                                      Set1Challenge1.Encoding.ASCII);
        String repStringHex = repStringData.getHex();
        Set1Challenge1.Data plaintextData
            = new Set1Challenge1.Data(plaintext,
                                      Set1Challenge1.Encoding.ASCII);
        String plaintextHex = plaintextData.getHex();

        String result = Set1Challenge2.fixedXOR(repStringHex, plaintextHex);
        return result;
    }

}
