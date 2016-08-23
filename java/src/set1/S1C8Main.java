/*************************************
 Joseph Winters
 Cryptopals Set 1 Challenge 8 Program
 Spring 2016
*************************************/


public class S1C8Main {

    public static void main(String[] args) {
        String filename = "S1C8Data.txt";
        Data.Encoding enc = Data.Encoding.HEX;

        String result;
        try {
            result = Set1Challenge8.aesECBFind(filename, enc);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("In file " + filename + ", the AES ECB encrypted line is:");
        System.out.println("\"" + result + "\"");
    }

}
