/***************************************
 Joseph Winters
 Cryptopals Set 1 Challenge 8 Functions
 Spring 2016
***************************************/


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.Arrays;


public class Set1Challenge8 {

    // Detect which line of the enc encoded data found in filename is encoded under AES in ECB mode
    //   and return an AESECBDecryption representing the decryption of that line.
    public static String aesECBFind(String filename, Data.Encoding enc)
    throws IOException {
        double bestScore = 0;
        double currScore;
        String bestLine = "";
        String currLine;
        try ( InputStream inStream = new FileInputStream(filename);
              InputStreamReader inStreamReader = new InputStreamReader(inStream, Charset.forName("UTF-8"));
              BufferedReader bufReader = new BufferedReader(inStreamReader) ) {
            while (true) {
                currLine = bufReader.readLine();
                if (currLine == null) break;
                currScore = scoreIsAESECB(currLine, enc);
                if (currScore > bestScore) {
                    bestLine = currLine;
                    bestScore = currScore;
                }
            }
        } catch(Exception e) {
            throw new IOException("Unable to access and read file " + filename, e);
        }
        return bestLine;
    }

    // Score how likely it is that enc encoded string ciphertext is encrypted under AES in ECB mode.
    private static double scoreIsAESECB(String ciphertext, Data.Encoding enc) {
        Data ciphertextData = new Data(ciphertext, enc);
        if (ciphertextData.getSize() % 16 != 0) {
            return 0;
        }
        ArrayList<byte[]> byteChunks = new ArrayList<byte[]>();
        for (int i = 0; i < ciphertextData.getSize() / 16; i++) {
            byteChunks.add(Arrays.copyOfRange(ciphertextData.getBytes(), i * 16, i * 16 + 16));
        }
        double numDuplicates = 0;
        for (int i = 0; i < byteChunks.size(); i++) {
            for (int j = i; j < byteChunks.size(); j++) {
                if (Arrays.equals(byteChunks.get(i), byteChunks.get(j))) {
                    numDuplicates++;
                }
            }
        }
        return numDuplicates / ciphertextData.getSize();
    }

}
