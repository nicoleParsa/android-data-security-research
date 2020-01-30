package com.example.password_protected_app;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class UnitTestingByNicoleParsa {
    @Test
    public void test_encryption(){
        //Encryption test
        String string = "My sensitive string that I want to encrypt";
        byte[] bytes = string.getBytes();
        com.example.password_protected_app.DataEncryptionByNicoleParsa de = new com.example.password_protected_app.DataEncryptionByNicoleParsa();
        HashMap<String, byte[]> map = de.encryptBytes(bytes, "UserSuppliedPassword");
        String expected = "salt=";  //can not expect the full ma becuase it is partially generated randomly; However, it has to start with "salt="
        assertEquals(expected, map.toString().substring(1, 6));
    }

    @Test
    public void test_decryption(){
        //Decryption test
        String myString = "My sensitive string that I want to encrypt";
        byte[] bytes = myString.getBytes();
        com.example.password_protected_app.DataEncryptionByNicoleParsa de = new com.example.password_protected_app.DataEncryptionByNicoleParsa();
        HashMap<String, byte[]> map = de.encryptBytes(bytes, "UserSuppliedPassword");

        com.example.password_protected_app.DataDecryptionByNicoleParsa dd = new com.example.password_protected_app.DataDecryptionByNicoleParsa();
        byte[] decrypted = dd.decryptData(map, "UserSuppliedPassword");
        if (decrypted != null)
        {
            String decryptedString = new String(decrypted);
            assertEquals(myString, decryptedString);
        }
    }
}