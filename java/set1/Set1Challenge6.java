/*
 * Joseph Winters
 * Cryptopals Set 1 Challenge 6 Functions
 * Spring 2016
 */


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.nio.charset.Charset;


public class Set1Challenge6 {

    /*** STEP 1 ***/

    // No code necessary

    /*** STEP 2 ***/

    // Return the Hamming distance between integers n1 and n2.
    public static int hammingDistance(int n1, int n2) {
        Data n1Data = new Data(n1);
        Data n2Data = new Data(n2);
        return hammingDistance(n1Data, n2Data);
    }

    // Return the Hamming distance between byte arrays ba1 and ba2.
    public static int hammingDistance(byte[] ba1, byte[] ba2) {
        Data ba1Data = new Data(ba1);
        Data ba2Data = new Data(ba2);
        return hammingDistance(ba1Data, ba2Data);
    }

    // Return the Hamming distance between s1 and s2, where both are strings with Encoding encoding.
    public static int hammingDistance(String s1, String s2, Data.Encoding encoding) {
        Data s1Data = new Data(s1, encoding);
        Data s2Data = new Data(s2, encoding);
        return hammingDistance(s1Data, s2Data);
    }

    // Return the Hamming distance between d1 and d2, where both are Data objects.
    public static int hammingDistance(Data d1, Data d2) {
        byte[] d1Bytes = d1.getBytes();
        byte[] d2Bytes = d2.getBytes();
        int hammingAccumulator = 0;
        for (int i = 0; i < d1Bytes.length; i++) {
            hammingAccumulator += hammingDistance(d1Bytes[i], d2Bytes[i]);
        }
        return hammingAccumulator;
    }

    // Return the Hamming distance between bytes b1 and b2.
    private static int hammingDistance(byte b1, byte b2) {
        int b1Bit;
        int b2Bit;
        int hammingAccumulator = 0;
        for (int i = 0; i < 8; i++) {
            b1Bit = (b1 >> i) & 1; // take a single bit
            b2Bit = (b2 >> i) & 1; // take a single bit
            if (b1Bit != b2Bit) {
                hammingAccumulator += 1;
            }
        }
        return hammingAccumulator;
    }

    /*** STEP 3 ***/

    // Return the average edit distance between two consecutive sections of
    //   keySize bytes from encryption, a Data object.
    // Requires: encryption.getSize() / keySize >= 4
    private static double normEditDistance(int keySize, Data encryption) {
        String ciphertextHex = encryption.getHex();
        int hexKeySize = 2 * keySize;
        String substr1 = ciphertextHex.substring(0, hexKeySize);
        String substr2 = ciphertextHex.substring(hexKeySize, 2 * hexKeySize);
        String substr3 = ciphertextHex.substring(2 * hexKeySize, 3 * hexKeySize);
        String substr4 = ciphertextHex.substring(3 * hexKeySize, 4 * hexKeySize);

        double diff1 = (double)hammingDistance(substr1, substr2, Data.Encoding.HEX);
        double diff2 = (double)hammingDistance(substr1, substr3, Data.Encoding.HEX);
        double diff3 = (double)hammingDistance(substr1, substr4, Data.Encoding.HEX);
        double diff4 = (double)hammingDistance(substr2, substr3, Data.Encoding.HEX);
        double diff5 = (double)hammingDistance(substr2, substr4, Data.Encoding.HEX);
        double diff6 = (double)hammingDistance(substr3, substr4, Data.Encoding.HEX);

        return (diff1 + diff2 + diff3 + diff4 + diff5 + diff6) / (6 * keySize);
    }

    /*** STEP 4 ***/

    // Return the most likely key size given encryption, a Data object.
    private static int repKeyXORKeySize(Data encryption) {
        int bestKeySize = 2;
        double minNormEditDistance = normEditDistance(bestKeySize, encryption);
        double currNormEditDistance;
        for (int i = 3; i < 41; i++) {
            currNormEditDistance = normEditDistance(i, encryption);
            if (currNormEditDistance < minNormEditDistance) {
                bestKeySize = i;
                minNormEditDistance = currNormEditDistance;
            }
        }
        return bestKeySize;
    }

    /*** STEP 5 ***/

    // Return an array of Data objects containing chunkSize bytes each from data (though the last
    //   Data object may have fewer bytes).
    private static Data[] chunkData(int chunkSize, Data data) {
        int resultLength = (data.getSize() / chunkSize)
                           + ((data.getSize() % chunkSize == 0 ? 0 : 1));
        Data[] result = new Data[resultLength];
        String dataHex = data.getASCII();   // since ASCII characters are bytes
        for (int i = 0; i < resultLength; i++) {
            if (i == resultLength - 1) {
                result[i] = new Data(dataHex.substring(i * chunkSize), Data.Encoding.ASCII);
                break;                      // this is last iteration
            }
            result[i] = new Data(dataHex.substring(i * chunkSize, (i+1) * chunkSize),
                                 Data.Encoding.ASCII);
        }
        return result;
    }

    /*** STEP 6 ***/

    // Return an array of Data objects that is the transpose of chunks.
    private static Data[] transposeChunks(Data[] chunks) {
        byte[][] chunksBytes = new byte[chunks.length][];
        for (int i = 0; i < chunks.length; i++) {
            chunksBytes[i] = chunks[i].getBytes();
        }

        int numShortColumns = chunksBytes[0].length - chunksBytes[chunks.length - 1].length;
        int numFullColumns = chunksBytes[0].length - numShortColumns;

        byte[][] resultBytes = new byte[chunksBytes[0].length][];
        for (int i = 0; i < numFullColumns; i++) {
            resultBytes[i] = new byte[chunks.length];
            for (int j = 0; j < chunks.length; j++) {
                resultBytes[i][j] = chunksBytes[j][i];
            }
        }
        for (int i = numFullColumns; i < numFullColumns + numShortColumns; i++) {
            resultBytes[i] = new byte[chunks.length - 1];
            for (int j = 0; j < chunks.length - 1; j++) {
                resultBytes[i][j] = chunksBytes[j][i];
            }
        }

        Data[] result = new Data[resultBytes.length];
        for (int i = 0; i < resultBytes.length; i++) {
            result[i] = new Data(resultBytes[i]);
        }
        return result;
    }

    /*** STEP 7 ***/

    // Return an array of XORDecryptions corresponding to the Data objects in cols.
    private static XORDecryption[] decryptArray(Data[] cols) {
        XORDecryption[] result = new XORDecryption[cols.length];
        for (int i = 0; i < cols.length; i++) {
            result[i] = Set1Challenge3.crackSingleCharXOR(cols[i].getHex());
        }
        return result;
    }

    /*** STEP 8 ***/

    // Return a RepKeyXORDecryption representing the most likely decryption of the encoding encoded
    //   data found in filename.
    public static RepKeyXORDecryption decryptFile(String filename, Data.Encoding encoding) {
        String currLine;
        String ciphertext = "";
        XORDecryption bestXORDecryption = null;
        XORDecryption currXORDecryption;
        try ( InputStream inStream = new FileInputStream(filename);
              InputStreamReader inStreamReader
                  = new InputStreamReader(inStream, Charset.forName("UTF-8"));
              BufferedReader bufReader = new BufferedReader(inStreamReader) ) {
            while (true) {
                currLine = bufReader.readLine();
                if (currLine == null) break;
                ciphertext += currLine;
            }
        } catch (Exception e) {
            System.out.println("Unable to access and read file " + filename);
        }

        Data fileData = new Data(ciphertext, encoding);
        int keySize = repKeyXORKeySize(fileData);
        Data[] chunks = chunkData(keySize, fileData);
        Data[] cols = transposeChunks(chunks);
        XORDecryption[] colDecryptions = decryptArray(cols);

        String key = "";
        for (int i = 0; i < keySize; i++) {
            key += colDecryptions[i].getKey();
        }

        String plaintext = "";
        char currChar;
        outer:
        for (int i = 0; i < chunks.length; i++) {
            for (XORDecryption dec : colDecryptions) {
                try {
                    currChar = dec.getPlaintext().charAt(i);
                    plaintext += currChar;
                } catch (IndexOutOfBoundsException e) {
                    break outer;
                }
            }
        }

        RepKeyXORDecryption result = new RepKeyXORDecryption(key, plaintext, ciphertext);
        return result;
    }

}
