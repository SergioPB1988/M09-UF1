package iticbcn.xifratge;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;
import java.security.*;
import java.util.Base64;

public class AES {

    public static final String CLAU = "claveSecreta123";  // Cambia la clave por una segura
    private static final String IV = "1234567890123456";  // IV de 16 bytes

    public static String cifrar(String texto) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(CLAU.getBytes(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] encrypted = cipher.doFinal(texto.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String descifrar(String textoCifrado) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(CLAU.getBytes(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] decodedBytes = Base64.getDecoder().decode(textoCifrado);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted);
    }
}