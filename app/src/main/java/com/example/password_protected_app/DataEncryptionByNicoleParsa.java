package com.example.password_protected_app;

import android.util.Log;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import javax.crypto.SecretKeyFactory;
import java.util.HashMap;
import javax.crypto.spec.PBEKeySpec;


public class DataEncryptionByNicoleParsa
{
    HashMap<String, byte[]> encryptBytes(byte[] plainTextBytes, String myPasswordString) {
        HashMap<String, byte[]> myHashMap = new HashMap<String, byte[]>();

        try {
            //generate salt using a random process
            byte saltArray[] = new byte[256];
            SecureRandom randomNum = new SecureRandom();
            randomNum.nextBytes(saltArray);

            //generate a secret key based on PBKDF2 on the password, not directly the password itself
            char[] passCharArray = myPasswordString.toCharArray(); //converting to char array
            PBEKeySpec keySpec = new PBEKeySpec(passCharArray, saltArray, 1324, 256); //iterations
            SecretKeyFactory secretKey = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] keyBytesArray = secretKey.generateSecret(keySpec).getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytesArray, "AES");

            //initialize AES using random procedure for IV
            SecureRandom ivRandomNum = new SecureRandom();
            byte[] iv = new byte[16];
            ivRandomNum.nextBytes(iv);
            IvParameterSpec ivSpecParam = new IvParameterSpec(iv);

            //Encryption is done below:
            Cipher cipherObj = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipherObj.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpecParam);
            byte[] encryptedByteArray = cipherObj.doFinal(plainTextBytes);

            myHashMap.put("salt", saltArray);
            myHashMap.put("iv", iv);
            myHashMap.put("encrypted", encryptedByteArray);
        } catch (Exception e) {
            System.out.println("encrypt: " + e);
        }
        return myHashMap;

    }
}
