/*
 * Joseph Winters
 * Cryptopals Set 1 Challenge 5 Program
 * Spring 2016
 */


public class S1C5Main {

    public static void main(String[] args) {
        String plaintext = "Burning 'em, if you ain't quick and nimble\nI go crazy when I hear a cymbal";
        String key = "ICE";
        String ciphertext = "0b3637272a2b2e63622c2e69692a23693a2a3c6324202d623d63343c2a26226324272765272a282b2f20430a652e2c652a3124333a653e2b2027630c692b20283165286326302e27282f";

        System.out.println("Encrypting the plaintext:");
        System.out.println("\"" + plaintext + "\"");
        System.out.println("with the key:");
        System.out.println("\"" + key + "\"");
        System.out.println("should give the (hexadecimal) ciphertext:");
        System.out.println("\"" + ciphertext + "\"");

        String result = Set1Challenge5.encryptWithRepKey(key, plaintext);

        if (result.equals(ciphertext)) {
            System.out.println("This is computed correctly by the program.");
        } else {
            System.out.println("The program does not compute this correctly.");
        }
    }

}
