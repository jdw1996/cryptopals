/*************************************
 Joseph Winters
 Cryptopals Set 1 Challenge 6 Program
 Spring 2016
*************************************/


public class S1C6Main {

    public static void main(String[] args) {
        String filename = "S1C6Data.txt";
        Data.Encoding enc = Data.Encoding.BASE64;

        RepKeyXORDecryption result;
        try {
            result = Set1Challenge6.repKeyXORDecryptFile(filename, enc);
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
