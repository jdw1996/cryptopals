/*
 * Joseph Winters
 * Cryptopals Set 1 Challenge 2 Program
 * Spring 2016
 */


public class S1C2Main {

    public static void main(String[] args) {
        String hex1 = "1c0111001f010100061a024b53535009181c";
        String hex2 = "686974207468652062756c6c277320657965";
        String hex3 = "746865206b696420646f6e277420706c6179";

        System.out.println("The XOR of the hex string:");
        System.out.println("\"" + hex1 + "\"");
        System.out.println("and the hex string:");
        System.out.println("\"" + hex2 + "\"");
        System.out.println("should be:");
        System.out.println("\"" + hex3 + "\"");

        String result = Set1Challenge2.fixedXOR(hex1, hex2);

        if (result.equals(hex3)) {
            System.out.println("This is computed correctly by the program.");
        } else {
            System.out.println("The program does not compute this correctly.");
            System.out.println("Instead, it computes:");
            System.out.println("\"" + result + "\"");
        }

        Set1Challenge1.Data data2 =
            new Set1Challenge1.Data(hex2, Set1Challenge1.Encoding.HEX);
        Set1Challenge1.Data data3 =
            new Set1Challenge1.Data(hex3, Set1Challenge1.Encoding.HEX);

        System.out.println("");
        System.out.println("The second string translates in ASCII to:");
        System.out.println("\"" + data2.getASCII() + "\"");
        System.out.println("and the third string translates in ASCII to:");
        System.out.println("\"" + data3.getASCII() + "\"");
    }

}
