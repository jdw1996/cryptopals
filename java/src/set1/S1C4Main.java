/*************************************
 Joseph Winters
 Cryptopals Set 1 Challenge 4 Program
 Spring 2016
*************************************/


public class S1C4Main {

    public static void main(String[] args) {
        String filename = "S1C4Data.txt";
        Data.Encoding encoding = Data.Encoding.HEX;
        XORDecryption result = Set1Challenge4.findAndDecrypt(filename, encoding);

        System.out.println("The hex string decrypted is:");
        System.out.println("\"" + result.getCiphertext() + "\"");
        System.out.println("and the decryption by single character XOR is:");
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
