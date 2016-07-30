/***************************************
 Joseph Winters
 Cryptopals Set 1 Challenge 1 Functions
 Spring 2016
***************************************/


import java.nio.ByteBuffer;
import java.util.Base64;


public class Set1Challenge1 {

    // Return the base-64 encoded string corresponding to hex string x.
    // NB: This function is only defined for the sake of the challenge. It is preferable to
    //   interface with Data objects where possible.
    public static String hexToBase64(String x) {
        Data datax = new Data(x, Data.Encoding.HEX);
        return datax.getBase64();
    }

}
