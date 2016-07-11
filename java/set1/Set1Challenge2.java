/*
 * Joseph Winters
 * Cryptopals Set 1 Challenge 2 Functions
 * Spring 2016
 */


public class Set1Challenge2 {

    // Return the result of XOR-ing the two Data objects datax and datay.
    public static Data fixedXOR(Data datax, Data datay) {
        byte[] bytesx = datax.getBytes();
        byte[] bytesy = datay.getBytes();
        byte[] bytesz = new byte[bytesx.length];
        for (int i = 0; i < bytesx.length; i++) {
            int bytex = (int)bytesx[i];
            int bytey = (int)bytesy[i];
            int bytez = bytex ^ bytey;
            bytesz[i] = (byte)(0xff & bytez);
        }
        Data dataz = new Data(bytesz);
        return dataz;
    }

}
