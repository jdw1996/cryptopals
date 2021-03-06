/*************************************
 Joseph Winters
 Cryptopals Set 1 Challenge 3 Program
 Spring 2016
**************************************/


public class S1C3Main {

    public static void main(String[] args) {
        String hex = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736";
        Data.Encoding enc = Data.Encoding.HEX;

        System.out.println("Given the hex string:");
        System.out.println("\"" + hex + "\"");

        XORDecryption result = Set1Challenge3.crackSingleCharXOR(hex, enc);

        System.out.println("the decryption by single character XOR is:");
        System.out.println("\"" + result.getPlaintext() + "\"");
        System.out.println("");
        System.out.println("This decryption received a score of "
                           + result.getScore()
                           + " (higher is better) and");
        System.out.println("was carried out with the character '"
                           + result.getKey()
                           + "'.");
    }

}
