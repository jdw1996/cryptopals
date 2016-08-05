/*************************************
 Joseph Winters
 Cryptopals Set 1 Challenge 7 Program
 Spring 2016
*************************************/


public class S1C7Main {

    public static void main(String[] args) {
        String key = "YELLOW SUBMARINE";
        String filename = "S1C7Data.txt";
        Data.Encoding enc = Data.Encoding.BASE64;

        AESECBDecryption result;
        try {
            result = Set1Challenge7.aesECBDecryptFile(key, filename, enc);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("The decryption of file " + filename + " is:");
        System.out.println("\"\"\"");
        System.out.println(result.getPlaintext());
        System.out.println("\"\"\"");
        System.out.println("This decryption was carried out with the key:");
        System.out.println("\"" + result.getKey() + "\"");
    }

}
