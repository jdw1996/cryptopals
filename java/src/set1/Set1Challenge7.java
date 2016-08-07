/***************************************
 Joseph Winters
 Cryptopals Set 1 Challenge 7 Functions
 Spring 2016
***************************************/


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.lang.IllegalArgumentException;

import java.nio.charset.Charset;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class Set1Challenge7 {

    // Return an AESECBDecryption representing the decryption of the enc encoded data found in
    //   filename, given the key key.
    public static AESECBDecryption aesECBDecryptFile(String key,
                                                     String filename,
                                                     Data.Encoding enc)
    throws IOException, IllegalArgumentException {
        String currLine;
        String ciphertext = "";
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
            throw new IOException("Unable to access and read file " + filename, e);
        }

        Data ciphertextData = new Data(ciphertext, enc);
        AESECBDecryption result;

        try {
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);

            String plaintext = new String(cipher.doFinal(ciphertextData.getBytes()));
            result = new AESECBDecryption(key, plaintext, ciphertextData);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to decrypt contents of file " + filename, e);
        }
        return result;
    }

}
