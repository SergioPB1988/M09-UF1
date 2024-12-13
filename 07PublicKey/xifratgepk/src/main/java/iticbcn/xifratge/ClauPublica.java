package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;


public class ClauPublica {

    // Método para generar un par de claves RSA
    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // Tamaño de la clave RSA
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    // Método para cifrar el mensaje con la clave pública
    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        return cipher.doFinal(msg.getBytes());
    }

    // Método para descifrar el mensaje con la clave privada
    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada);
        byte[] decodedBytes = cipher.doFinal(msgXifrat);
        return new String(decodedBytes);
    }
}
