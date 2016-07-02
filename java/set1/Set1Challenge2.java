/*
 * Joseph Winters
 * Cryptopals Set 1 Challenge 2 Functions
 * Spring 2016
 */


public class Set1Challenge2 {

    // Return the result of xor-ing the two hex strings x and y.
    public static String fixedXOR(String x, String y) {
        Set1Challenge1.Data datax
            = new Set1Challenge1.Data(x, Set1Challenge1.Encoding.HEX);
        Set1Challenge1.Data datay
            = new Set1Challenge1.Data(y, Set1Challenge1.Encoding.HEX);
        byte[] bytesx = datax.getBytes();
        byte[] bytesy = datay.getBytes();
        byte[] bytesz = new byte[bytesx.length];
        for (int i = 0; i < bytesx.length; i++) {
            bytesz[i] = (byte)(bytesx[i] ^ bytesy[i]);
        }
        Set1Challenge1.Data dataz = new Set1Challenge1.Data(bytesz);
        return dataz.getHex();
    }

}
