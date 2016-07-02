/*
 * Joseph Winters
 * Cryptopals Set 1 Challenge 3 Program
 * Spring 2016
 */


public class S1C3Main {

    public static void main(String[] args) {
        String hex = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736";

        System.out.println("Given the hex string:");
        System.out.println("\"" + hex + "\"");

        Set1Challenge3.Decryption result
            = Set1Challenge3.crackSingleCharXOR(hex);

        System.out.println("the decryption by single character XOR is:");
        System.out.println("\"" + result.getDecryption() + "\"");
        System.out.println("");
        System.out.println("This decryption received a score of "
                            + result.getScore()
                            + " (higher is better) and");
        System.out.println("was carried out with the character '"
                           + result.getKey()
                           + "'.");
    }

}
