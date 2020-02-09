package com.example.password_protected_app;

import android.util.Log;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import javax.crypto.SecretKeyFactory;
import java.util.HashMap;
import javax.crypto.spec.PBEKeySpec;

public class DataDecryptionByNicoleParsa
{
    byte[] decryptData(HashMap<String, byte[]> hashMap, String myPasswordString)
    {
        byte[] decryptedByteArray = null;
        try
        {
            byte saltArray[] = hashMap.get("salt");
            byte ivArray[] = hashMap.get("iv");
            byte encryptedByteArray[] = hashMap.get("encrypted");

            //regenerate secret key from password
            char[] passCharArray = myPasswordString.toCharArray();
            PBEKeySpec keySpec = new PBEKeySpec(passCharArray, saltArray, 1324, 256);
            SecretKeyFactory secretKey = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] keyBytesArray = secretKey.generateSecret(keySpec).getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytesArray, "AES");

            //Decryption is done below:
            Cipher cipherObj = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivSpec = new IvParameterSpec(ivArray);
            cipherObj.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
            decryptedByteArray = cipherObj.doFinal(encryptedByteArray);
        }
        catch(Exception e)
        {
            System.out.println("decrypt: " + e);
        }

        return decryptedByteArray;
    }
}
