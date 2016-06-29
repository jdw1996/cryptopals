/*
 * Joseph Winters
 * Cryptopals Set 1 Challenge 1 Program
 * Spring 2016
 */


public class S1C1Main {

    public static void main(String[] args) {
        String hex = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";
        String base64 = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t";

        System.out.println("Given the hex string:");
        System.out.println("\"" + hex + "\"");
        System.out.println("the base-64 encoding should be:");
        System.out.println("\"" + base64 + "\"");

        Set1Challenge1.Data data
            = new Set1Challenge1.Data(hex, Set1Challenge1.Data.Encoding.HEX);

        if (data.getBase64().equals(base64)) {
            System.out.println("This is computed correctly by the program.");
        } else {
            System.out.println("The program does not compute this correctly.");
        }

        System.out.println("");
        System.out.println("This string translates in ASCII to:");
        System.out.println("\"" + data.getASCII() + "\"");
    }

}
